package com.huacainfo.ace.wxms.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.tools.StringKit;
import com.huacainfo.ace.common.web.controller.BaseController;
import com.huacainfo.ace.wxms.model.WechatConfig;
import com.huacainfo.ace.wxms.service.WechatConfigService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName AccessTokenController
 * @Description 公众号||小程序accetoken获取控制器
 * @Author Arvin Zou
 * @Date 2019/8/29 15:48
 */
@RestController
@RequestMapping("/www/config")
public class ConfigController extends BaseController {

    @Resource
    private WechatConfigService configService;

    /**
     * 根据系统ID查找 微信配置信息
     *
     * @param sysId 系统ID
     * @return WechatConfig
     */
    @GetMapping(value = "/findBySysId", produces = "application/json;charset=UTF-8")
    public ResponseDTO getConfig(String sysId) {
        if (StringKit.isEmpty(sysId)) {
            return new ResponseDTO(ResultCode.FAIL, "缺少必要参数");
        }
        WechatConfig config = configService.findBySysId(sysId);
        if (config == null) {
            return new ResponseDTO(ResultCode.FAIL, "未找到相关数据");
        }
        return new ResponseDTO(ResultCode.SUCCESS, "获取成功", config);
    }

    /**
     * 手动刷新 公众号/小程序 accessToken令牌
     *
     * @param sysId 使用系统ID
     * @return ResponseDTO
     */
    @GetMapping(value = "/refresh", produces = "application/json;charset=UTF-8")
    public ResponseDTO refreshConfig(String sysId, String token) {
        if (StringKit.isEmpty(sysId)) {
            return new ResponseDTO(ResultCode.FAIL, "缺少必要参数");
        }
        //简单权限验证
        if (!"aovnxhxtj06kuxilhwu72ov5m89421yy".equals(token)) {
            return new ResponseDTO(ResultCode.FAIL, "权限不足,无法调用");
        }

        return configService.refreshConfig(sysId);
    }
}
