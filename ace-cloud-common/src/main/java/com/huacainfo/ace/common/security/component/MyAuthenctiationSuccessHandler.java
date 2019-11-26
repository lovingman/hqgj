package com.huacainfo.ace.common.security.component;

import com.alibaba.fastjson.JSON;
import com.huacainfo.ace.common.constant.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.huacainfo.ace.common.tools.JwtTokenUtil;
import com.huacainfo.ace.common.dto.ResponseDTO;
@Component("myAuthenctiationSuccessHandler")
public class MyAuthenctiationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Value("${jwt.expiration}")
    private Long expiration;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        logger.info("登录成功");
        response.setContentType("application/json;charset=UTF-8");
        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails=(UserDetails)obj;
        String token = jwtTokenUtil.generateToken(userDetails);
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        ResponseDTO<Map<String, String>> rst=new ResponseDTO(ResultCode.SUCCESS,"登录成功!");
        rst.setData(tokenMap);
        logger.debug("{}",tokenMap);
        this.redisTemplate.delete(userDetails.getUsername());
        this.redisTemplate.opsForValue().set(userDetails.getUsername(),JSON.toJSONString(obj));
        logger.debug("{} in redis {}",userDetails.getUsername(),JSON.toJSONString(obj));
        response.getWriter().write(JSON.toJSONString(rst));
    }


}
