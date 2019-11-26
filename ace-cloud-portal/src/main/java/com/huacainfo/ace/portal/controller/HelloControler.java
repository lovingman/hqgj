package com.huacainfo.ace.portal.controller;

import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.common.web.controller.BaseController;
import com.huacainfo.ace.portal.dao.SystemDao;
import com.huacainfo.ace.portal.service.SecurityService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@Api(tags = "测试类", hidden = true)
public class HelloControler extends BaseController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private SystemDao systemDao;
    @Autowired
    private SecurityService securityService;

    //@Log(operationObj="测试",operationType="获取系统登录用户",detail = "通过会话共享获取")
    @GetMapping(value = "/get", produces = "application/json;charset=UTF-8")
    public UserProp getSession() {
        UserProp id = this.getCurUserProp();
        securityService.loadConfig("sys");
        logger.info("================>portal service get -> {}", id);
        return id;
    }

    @PostMapping(value = "/set", produces = "application/json;charset=UTF-8")
    public Object setSession(HttpServletRequest request) {
        String id = "900000";
        request.getSession().setAttribute("K900000", id);
        logger.info("================>portal service set -> {}", id);
        return id;
    }

    @GetMapping(value = "/feign")
    public UserProp feign() {
        logger.info("================>portal service feign -> {}", new Date());
        UserProp rst = new UserProp();
        rst.setAccount("admin");
        return rst;
    }
}
