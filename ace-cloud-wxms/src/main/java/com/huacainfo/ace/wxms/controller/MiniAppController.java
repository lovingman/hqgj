package com.huacainfo.ace.wxms.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.tools.StringKit;
import com.huacainfo.ace.common.web.controller.BaseController;
import com.huacainfo.ace.wxms.service.MiniAppService;
import com.huacainfo.ace.wxms.wechat.entity.pojo.MiniAppAuthData;
import com.huacainfo.ace.wxms.wechat.entity.pojo.MiniUserInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName MiniAppController
 * @Description 微信小程序相关
 * @Author Arvin Zou
 * @Date 2019/10/8 14:31
 */
@RestController
@RequestMapping("/www/mini")
public class MiniAppController extends BaseController {


    @Resource
    private MiniAppService miniAppService;

    /**
     * 小程序用户信息解密/session登录等
     *
     * @param sysId  分配的系统ID
     * @param jscode 登录时获取的 code；调用 wx.login() 获取 临时登录凭证code
     * @param data   wx.getUserInfo 接口返回参数
     *               rawData,signature,encryptedData,iv 为必须传入参数;其他为可选
     * @return ResponseDTO
     */
    @PostMapping(value = "/authority", produces = "application/json;charset=UTF-8")
    public ResponseDTO<MiniUserInfo> authority(String sysId, String jscode, MiniAppAuthData data) throws Exception {
        if (!StringKit.areNotEmpty(jscode,
                data.getRawData(),
                data.getSignature(),
                data.getEncryptedData(),
                data.getIv())) {
            return new ResponseDTO<>(ResultCode.FAIL, "缺少必要参数");
        }

        return miniAppService.authority(sysId, jscode, data);
    }
}
