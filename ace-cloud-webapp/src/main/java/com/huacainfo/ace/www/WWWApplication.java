package com.huacainfo.ace.www;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
@SpringBootApplication
@EnableEurekaClient
public class WWWApplication {

    public static void main(String[] args) {
        SpringApplication.run(WWWApplication.class, args);
    }

}
