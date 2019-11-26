package com.huacainfo.ace.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication(scanBasePackages = {"com.huacainfo.ace"})
@ComponentScan({"com.huacainfo.ace"})
@EnableEurekaClient
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 86400 * 30)
@EnableZuulProxy
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.huacainfo.ace")
@ServletComponentScan(basePackages = "com.huacainfo.ace.common.security.component")
public class ZuulServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulServerApplication.class, args);
    }

}
