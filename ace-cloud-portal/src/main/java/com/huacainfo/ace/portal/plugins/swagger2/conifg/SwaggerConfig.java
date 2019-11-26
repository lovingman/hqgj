package com.huacainfo.ace.portal.plugins.swagger2.conifg;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName SwaggerConfig
 * @Description TODO
 * @Author Arvin Zou
 * @Date 2019/5/15 17:38
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {

    @Value("${swagger.scan}")
    private String API_SCAN_PACKAGE;

    @Value("${swagger.enable}")
    private boolean IS_ENABLE;

    /**
     * 因为 Swagger2的资源文件都是在jar包里面，如果不在此处配置加载静态资源，
     * 会导致请求http://localhost:8081/swagger-ui.html失败
     * <!--swagger资源配置-->
     * <mvc:resources location="classpath:/META-INF/resources/" mapping="swagger-ui.html"/>
     * <mvc:resources location="classpath:/META-INF/resources/webjars/" mapping="/webjars/**"/>
     *
     * @param registry registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }


    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(API_SCAN_PACKAGE))
                .paths(PathSelectors.any())
                .build()
                .enable(IS_ENABLE);

    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot中使用Swagger2构建RESTful APIs")
                .description("ace-cloud-portal后端接口文档:  http://localhost:9016/swagger-ui.html")
                //服务条款网址
                .license("湖南华彩伟业网络科技有限公司")
                .licenseUrl("http://zx.huacainfo.com/portal")
                .version("1.0")
                .build();
    }
}
