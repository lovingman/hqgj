package com.huacainfo.ace.portal;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(scanBasePackages = {"com.huacainfo.ace"})
@EnableEurekaClient
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 86400 * 30)
@MapperScan("com.huacainfo.ace.portal.dao")
@EnableFeignClients(basePackages = "com.huacainfo.ace")
@EnableTransactionManagement
@ServletComponentScan(basePackages = "com.huacainfo.ace.common.security.component")
@EnableScheduling
@Import({FdfsClientConfig.class})
@EnableCaching
public class PortalServerApplication implements WebMvcConfigurer {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) {
        SpringApplication.run(PortalServerApplication.class, args);

    }

}

