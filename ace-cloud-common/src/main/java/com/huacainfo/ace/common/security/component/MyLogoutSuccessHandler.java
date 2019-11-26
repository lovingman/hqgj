package com.huacainfo.ace.common.security.component;

import com.alibaba.fastjson.JSON;
import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.ResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Component("myLogoutSuccessHandler")
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        logger.info("退出成功");
        response.setContentType("application/json;charset=UTF-8");
        ResponseDTO<Map<String, String>> rst=new ResponseDTO(ResultCode.SUCCESS,"退出成功!");
        response.getWriter().write(JSON.toJSONString(rst));
    }
}
