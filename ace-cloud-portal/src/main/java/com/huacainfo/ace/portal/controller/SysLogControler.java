package com.huacainfo.ace.portal.controller;


import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.log.model.SysLog;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.portal.service.SysLogService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/feign")
@Api(tags = "系统日志feign接口", hidden = true)
public class SysLogControler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SysLogService sysLogService;


    @PostMapping(value = "/aspectLog")
    ResponseDTO aspectLog(@RequestBody SysLog log) throws Exception {
        logger.info("{}", log);
        return this.sysLogService.aspectLog(log);
    }

    @PostMapping(value = "/log")
    ResponseDTO log(@RequestParam String operationObj, @RequestParam String operationType, @RequestParam String describe, @RequestBody UserProp userProp) throws Exception {
        return this.sysLogService.log(operationObj, operationType, describe, userProp);
    }


}
