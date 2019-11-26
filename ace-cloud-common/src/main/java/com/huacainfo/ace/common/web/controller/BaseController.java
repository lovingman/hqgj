package com.huacainfo.ace.common.web.controller;

import com.alibaba.fastjson.JSON;
import com.huacainfo.ace.common.constant.CommonKeys;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.JwtTokenUtil;
import com.huacainfo.ace.common.vo.UserProp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

public class BaseController implements Serializable {

    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private StringRedisTemplate redisTemplate;

    protected HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
    }

    protected UserProp getCurUserProp() {
        String authHeader = getRequest().getHeader(this.tokenHeader);
        if (authHeader != null && authHeader.startsWith(this.tokenHead)) {
            logger.info("tokenHeader {}",authHeader);
            // The part after "Bearer "
            String authToken = authHeader.substring(this.tokenHead.length());
            String username = jwtTokenUtil.getUserNameFromToken(authToken);
            if (StringUtils.isEmpty(username)) {
                logger.info("empty username check authToken from tokenHeader {}",authHeader);
                return null;
            }

            String text=redisTemplate.opsForValue().get(username);
            UserProp userProp = JSON.parseObject(text,UserProp.class);
            if(CommonUtils.isBlank(userProp)){
                logger.info("empty redis for key {}  check time expiration ",username);
                return null;
            }

            if (jwtTokenUtil.validateToken(authToken, userProp.getAccount())) {
                return JSON.parseObject(text,UserProp.class);
            }
        }else{
            Object object = getRequest().getSession().getAttribute(CommonKeys.SESSION_USERINFO_KEY);
            return (UserProp) object;
        }

        return null;
    }


}
