package com.huacainfo.ace.wxms.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.tools.StringKit;
import com.huacainfo.ace.common.web.controller.BaseController;
import com.huacainfo.ace.wxms.model.MapWechatSys;
import com.huacainfo.ace.wxms.service.MiniAppService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName FeignController
 * @Description TODO
 * @Author Arvin Zou
 * @Date 2019/10/9 11:09
 */
@RestController
@RequestMapping("/feign")
public class FeignController extends BaseController {

    @Resource
    private MiniAppService miniAppService;


    /**
     * 微信绑定
     *
     * @param data 发送数据
     * @return ResponseDTO
     */
    @PostMapping(value = "/bindWechat", produces = "application/json;charset=UTF-8")
    public ResponseDTO bindWechat(MapWechatSys data) {

        if (!StringKit.areNotEmpty(data.getUnionId(), data.getUserId(), data.getSysId())) {
            return new ResponseDTO(ResultCode.FAIL, "缺少必要参数");
        }

        return miniAppService.bindWechat(data);
    }

}
