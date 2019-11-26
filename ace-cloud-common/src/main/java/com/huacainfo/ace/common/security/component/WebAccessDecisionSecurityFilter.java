package com.huacainfo.ace.common.security.component;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.constant.CommonKeys;
import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.security.vo.UserDetailsImpl;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;

@Component
@ServletComponentScan
@WebFilter(urlPatterns = "/*", filterName = "WebAccessDecisionSecurityFilter")
public class WebAccessDecisionSecurityFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Value("${filter.ignoreuris}")
    private String[] ignoreuris;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void init(FilterConfig filterConfig) {
        for (String o : ignoreuris) {
            logger.debug("ignoreuris ==> {}", o);
        }

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        String uri = httpReq.getRequestURI();
        // logger.debug("==>uri: {}",uri);
        boolean ignore = false;
        for (String o : ignoreuris) {
            if (uri.startsWith(o)) {
                logger.debug("==> ignoreuri: {} uri: {}", o, uri);
                ignore = true;
            }
        }

        String authHeader = httpReq.getHeader(this.tokenHeader);
        if (authHeader != null && authHeader.startsWith(this.tokenHead)) {
            String authToken = authHeader.substring(this.tokenHead.length());// The part after "Bearer "
            String username = jwtTokenUtil.getUserNameFromToken(authToken);
            if (!StringUtils.isEmpty(username)) {
                String text = redisTemplate.opsForValue().get(username);
                //logger.debug("==>{}",text);

                UserDetailsImpl userDetails = JSON.parseObject(text, UserDetailsImpl.class);

                JSONObject o = JSON.parseObject(text);
                Collection<GrantedAuthority> auths = new HashSet<GrantedAuthority>();

                for (Object obj : o.getJSONArray("authorities")) {
                    JSONObject temp = (JSONObject) obj;
                    GrantedAuthority e = new CustomGrantedAuthorityImpl(temp.getString("role"));
                    auths.add(e);
                }
                userDetails.setAuthorities(auths);

                //logger.debug("==>userDetails {}",userDetails);

                if (!CommonUtils.isBlank(userDetails)) {
                    if (jwtTokenUtil.validateToken(authToken, userDetails.getUsername())) {
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpReq));
                        //logger.info("authenticated user:{}", username);
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                        ignore = true;
                    }
                }
                /*try {
                    Thread.sleep(600);
                    logger.debug("iThread.sleep(600)");
                }catch (InterruptedException e){
                    logger.error("{}", e);
                }*/
            }
        } else {
            HttpSession session = httpReq.getSession();
            Object object = session.getAttribute(CommonKeys.SESSION_USERINFO_KEY);
            if (object != null) {
                ignore = true;
            }
        }
        if (ignore) {
            chain.doFilter(request, response);
        } else {
            response.setContentType("application/json;charset=UTF-8");
            ResponseDTO rst = new ResponseDTO(ResultCode.UNAUTHORIZED, "身份未验证,请先登录！");
            response.getWriter().write(JSON.toJSONString(rst));
        }

    }

    @Override
    public void destroy() {

    }
}
