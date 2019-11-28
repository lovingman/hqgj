package com.huacainfo.ace.hqgj;


import com.github.tobato.fastdfs.FdfsClientConfig;
import org.mybatis.spring.annotation.MapperScan;
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

/**
 * @ClassName HqgjApplication
 * @Description TODO
 */
@EnableCaching
@EnableScheduling
@EnableEurekaClient
@EnableTransactionManagement
@Import({FdfsClientConfig.class})
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 86400 * 30)
@MapperScan("com.huacainfo.ace.hqgj.dao")
@EnableFeignClients(basePackages = "com.huacainfo.ace")
@SpringBootApplication(scanBasePackages = {"com.huacainfo.ace"})
@ServletComponentScan(basePackages = "com.huacainfo.ace.common.security.component")
public class HqgjApplication {

    public static void main(String[] args) {

        SpringApplication.run(HqgjApplication.class, args);
    }


}
