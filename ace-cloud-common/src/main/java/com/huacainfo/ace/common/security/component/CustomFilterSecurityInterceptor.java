package com.huacainfo.ace.common.security.component;

import com.huacainfo.ace.common.constant.CommonKeys;
import com.huacainfo.ace.common.security.feign.FeignSecurityService;
import com.huacainfo.ace.common.security.vo.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 该过滤器的主要作用就是通过spring著名的IoC生成securityMetadataSource。
 * securityMetadataSource相当于本包中自定义的MyInvocationSecurityMetadataSourceService。
 * 该MyInvocationSecurityMetadataSourceService的作用提从数据库提取权限和资源，装配到HashMap中，
 * 供Spring Security使用，用于权限校验。
 *
 * @author sparta 11/3/29
 */

public class CustomFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {


    Logger logger = LoggerFactory.getLogger(this.getClass());
    private FeignSecurityService feignSecurityService;
    private CustomInvocationSecurityMetadataSource customInvocationSecurityMetadataSource;

    public CustomInvocationSecurityMetadataSource getCustomInvocationSecurityMetadataSource() {
        return customInvocationSecurityMetadataSource;
    }

    public void setCustomInvocationSecurityMetadataSource(CustomInvocationSecurityMetadataSource customInvocationSecurityMetadataSource) {
        this.customInvocationSecurityMetadataSource = customInvocationSecurityMetadataSource;
    }

    public FeignSecurityService getFeignSecurityService() {
        return feignSecurityService;
    }

    public void setFeignSecurityService(FeignSecurityService feignSecurityService) {
        this.feignSecurityService = feignSecurityService;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {

        return this.customInvocationSecurityMetadataSource;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        if (httpReq.getSession().getAttribute(CommonKeys.SESSION_USERINFO_KEY) == null) {
            Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (obj instanceof UserDetailsImpl) {
                UserDetailsImpl userDetails = (UserDetailsImpl) obj;
                if (userDetails != null) {
                    this.logger.info("加载登录用户到会话");
                    Map<String, String> cfg = (Map<String, String>) httpReq.getSession().getServletContext().getAttribute(CommonKeys.cfg);
                    Map<String, String> map = feignSecurityService.loadConfig(userDetails.getActiveSyId());
                    Map<String, String> sessionCfg = new HashMap<String, String>();
                    sessionCfg.putAll(cfg);
                    sessionCfg.putAll(map);
                    this.logger.info("加载登录用户个人配置到会话");
                    userDetails.setCfg(sessionCfg);
                    httpReq.getSession().setAttribute(CommonKeys.SESSION_USERINFO_KEY, userDetails);
                    logger.info("{}", userDetails);
                    logger.info("{}", sessionCfg);
                }
            }
        }
        FilterInvocation fi = new FilterInvocation(request, response, chain);
        //HttpServletResponse rsp = (HttpServletResponse) response;
        try {
            invoke(fi);
        } catch (Exception e) {
            this.logger.error("{}", e);
        }


    }

    @Override
    public Class<? extends Object> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    public void invoke(FilterInvocation fi) throws IOException,
            ServletException {
        InterceptorStatusToken token = super.beforeInvocation(fi);

        try {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } finally {
            super.afterInvocation(token, null);
        }

    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterconfig) {


    }

}
