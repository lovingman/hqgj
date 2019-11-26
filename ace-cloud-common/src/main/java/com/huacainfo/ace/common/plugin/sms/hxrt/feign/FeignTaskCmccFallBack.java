package com.huacainfo.ace.common.plugin.sms.hxrt.feign;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.ResponseDTO;
import org.springframework.stereotype.Component;

/**
 * @ClassName FeginTaskCmccFallBack
 * @Description TODO
 * @Author Arvin Zou
 * @Date 2019/8/6 9:20
 */
@Component
public class FeignTaskCmccFallBack implements FeignTaskCmccService {
    @Override
    public ResponseDTO addTaskCmcc(String taskName, String message, String mobileStr) {

        return new ResponseDTO(ResultCode.FAIL, "服务调用失败！");
    }
}
