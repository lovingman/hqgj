package com.huacainfo.ace.common.log.feign;

import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.log.model.SysLog;
import com.huacainfo.ace.common.vo.UserProp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "hqgj-portal", fallback = SysLogFallBack.class)
public interface SysLogService {


    @RequestMapping(value = "/feign/aspectLog")
    ResponseDTO aspectLog(@RequestBody SysLog log) throws Exception;

    @RequestMapping(value = "/feign/log")
    ResponseDTO log(@RequestParam String operationObj, @RequestParam String operationType, @RequestParam String describe, @RequestBody UserProp userProp) throws Exception;

}
