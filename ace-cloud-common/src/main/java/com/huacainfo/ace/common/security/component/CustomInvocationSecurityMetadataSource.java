package com.huacainfo.ace.common.security.component;

import com.huacainfo.ace.common.security.feign.FeignSecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 最核心的地方，就是提供某个资源对应的权限定义，即getAttributes方法返回的结果。 此类在初始化时，应该取到所有资源及其对应角色的定义。
 */
@Component
public class CustomInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private FeignSecurityService feignSecurityService;
    private AntPathMatcher urlMatcher = new AntPathMatcher();
    private Map<String, Collection<ConfigAttribute>> resource;

    public static void main(String[] args) {

        System.out.println("/portal/role/del.fil".endsWith("/role/del.fil"));
        System.out.println("/portal/role/del.fil".endsWith("/role/del1.fil"));
    }

    public void loadResourceDefine() {
        this.logger.info("加载资源控制列表");
        // 在Web服务器启动时，提取系统中的所有权限。
        this.resource = new ConcurrentHashMap<String, Collection<ConfigAttribute>>();
        List<Map<String, String>> list = feignSecurityService.loadResourceDefine();
        if (list == null || list.isEmpty()) {


            return;
        }

        for (Map<String, String> map : list) {
            Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
            if (map.get("roles") != null) {
                for (String role : map.get("roles").split(",")) {
                    ConfigAttribute ca = new SecurityConfig(role);
                    atts.add(ca);
                }
            }
            logger.info("{},{}", map.get("resources"), map.get("roles"));
            if (map.get("resources") != null) {
                resource.put(map.get("resources"), atts);
            }

        }
    }

    // 根据URL，找到相关的权限配置。
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {

        String url = this.getRequestRi(((FilterInvocation) object).getFullRequestUrl());
        int firstQuestionMarkIndex = url.indexOf("?");
        if (firstQuestionMarkIndex != -1) {
            url = url.substring(0, firstQuestionMarkIndex);
        }
        return getAttributes(url);
    }

    private Collection<ConfigAttribute> getAttributes(String url) {
        Collection<ConfigAttribute> rstAttributes = null;
        //logger.info("当前URL：{}", url);
        if (resource == null || resource.isEmpty()) {
            return null;
        }
        if (resource.containsKey(url)) {
            rstAttributes = resource.get(url);
        } else {
            Iterator<String> ite = resource.keySet().iterator();
            while (ite.hasNext()) {
                String resURL = ite.next();
                //urlMatcher.match(url, resURL)
                if (resURL.endsWith(url)) {
                    rstAttributes = resource.get(resURL);
                }
            }
        }
        //logger.info("rstAttributes {}", rstAttributes);
        return rstAttributes;
    }

    String getRequestRi(String url) {
        if (url.indexOf("?") != -1) {
            url = url.substring(0, url.indexOf("?"));
        }
        String a = (url.split("//"))[1];
        String b = a.split("/")[0];
        return a.substring(b.length(), a.length());
    }

    @Override
    public boolean supports(Class<?> arg0) {

        return true;
    }
    @Scheduled(cron = "0 */5 * * * ?")
    public void taskLoadResource() {
        this.logger.info("自动加载权限列表 {}", new Date());
        this.loadResourceDefine();

    }

}
