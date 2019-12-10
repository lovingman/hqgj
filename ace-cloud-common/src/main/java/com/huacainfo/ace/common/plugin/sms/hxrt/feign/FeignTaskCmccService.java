package com.huacainfo.ace.common.plugin.sms.hxrt.feign;

import com.huacainfo.ace.common.dto.ResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName FeignTaskCmccService
 * @Description TODO
 * @Author Arvin Zou
 * @Date 2019/8/5 20:24
 */
@FeignClient(value = "hqgj-portal", fallback = FeignTaskCmccFallBack.class)
public interface FeignTaskCmccService {

    @RequestMapping(value = "/feign/sms/addTaskCmcc", produces = "application/json;charset=UTF-8")
    ResponseDTO addTaskCmcc(@RequestParam("taskName") String taskName,
                            @RequestParam("message") String message,
                            @RequestParam("mobileStr") String mobileStr);
}
