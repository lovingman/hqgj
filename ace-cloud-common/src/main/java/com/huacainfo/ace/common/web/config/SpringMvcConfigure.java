package com.huacainfo.ace.common.web.config;

import com.alibaba.fastjson.JSON;
import com.huacainfo.ace.common.dto.ResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 本类仅为自定义异常拦截处理器统一处理系统错误
 *
 * @author chenxiaoke
 * @date 2019-04-29
 */
@Configuration
public class SpringMvcConfigure implements WebMvcConfigurer {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    /**
     * 自定义异常拦截处理器
     *
     * @param exceptionResolvers
     */
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers
                .add((HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) -> {
                    ResponseDTO responseDTO = new ResponseDTO();
                    responseDTO.setStatus(0);
                    responseDTO.setMessage(e.getMessage());
                    responseResponse(response, responseDTO);
                    return new ModelAndView();
                });
    }
    private void responseResponse(HttpServletResponse response, ResponseDTO responseDTO) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(200);
        try {
            response.getWriter().write(JSON.toJSONString(responseDTO));
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}






