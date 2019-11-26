package com.huacainfo.ace.common.security.component;

import com.huacainfo.ace.common.security.feign.FeignSecurityService;
import es.moki.ratelimitj.core.limiter.request.RequestLimitRule;
import es.moki.ratelimitj.core.limiter.request.RequestRateLimiter;
import es.moki.ratelimitj.inmemory.request.InMemorySlidingWindowRequestRateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class AuthenticationFailureEventListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    Set<RequestLimitRule> rules = Collections.singleton(RequestLimitRule.of(10, TimeUnit.MINUTES, 3));
    RequestRateLimiter limiter = new InMemorySlidingWindowRequestRateLimiter(rules);
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private FeignSecurityService feignSecurityService;

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
        if (event.getException().getClass().equals(UsernameNotFoundException.class)) {
            return;
        }
        String account = event.getAuthentication().getName();
        boolean reachLimit = limiter.overLimitWhenIncremented(account);
        logger.info("监听到用户{},密码输入错误", account);
        if (reachLimit) {
            logger.error("用户{},输入密码错误超过三次系统进行用户锁定", account);
            this.feignSecurityService.updateUserLocked(account);
        }

    }
}

