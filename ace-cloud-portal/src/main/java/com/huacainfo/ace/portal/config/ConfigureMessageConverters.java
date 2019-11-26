package com.huacainfo.ace.portal.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 本类仅为展示如何使用 Fastjson 替换 Spring Boot 默认使用的 Jackson
 *
 * @author chenxiaoke
 * @date 2019-04-29
 */
@Configuration
public class ConfigureMessageConverters extends WebMvcConfigurationSupport {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 自定义消息转换器：使用 Fastjson 替换 Spring Boot 默认使用的 Jackson
     *
     * @param converters
     */
    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(this.getHttpMessageConverter());
        super.addDefaultHttpMessageConverters(converters);
    }

    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        Iterator<HttpMessageConverter<?>> iterator = converters.iterator();
        while (iterator.hasNext()) {
            HttpMessageConverter<?> converter = iterator.next();
            if (converter instanceof MappingJackson2HttpMessageConverter) {
                iterator.remove();
            }
        }
        for (HttpMessageConverter<?> converter : converters) {
            log.info("HttpMessageConverter => {}", converter.getClass().getName());
        }

    }

    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        return new HttpMessageConverters(this.getHttpMessageConverter());
    }

    private HttpMessageConverter<?> getHttpMessageConverter() {
        // 1.需要定义一个Convert转换消息的对象
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        // 2.添加fastjson的配置信息，比如是否要格式化返回的json数据
        FastJsonConfig config = new FastJsonConfig();
        config.setSerializerFeatures(
                SerializerFeature.QuoteFieldNames,
                SerializerFeature.WriteDateUseDateFormat
        );
        fastJsonHttpMessageConverter.setFastJsonConfig(config);
        // 3.在convert中添加配置信息
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastJsonHttpMessageConverter.setSupportedMediaTypes(fastMediaTypes);

        return fastJsonHttpMessageConverter;
    }
}
