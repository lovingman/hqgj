package com.huacainfo.ace.wxms.service;

import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.wxms.model.MapWechatSys;
import com.huacainfo.ace.wxms.wechat.entity.pojo.MiniAppAuthData;
import com.huacainfo.ace.wxms.wechat.entity.pojo.MiniUserInfo;

/**
 * @ClassName MiniAppService
 * @Description TODO
 * @Author Arvin Zou
 * @Date 2019/10/8 15:44
 */
public interface MiniAppService {
    /**
     * 小程序用户信息解密/session登录等
     *
     * @param sysId  分配的系统ID
     * @param jscode 登录时获取的 code；调用 wx.login() 获取 临时登录凭证code
     * @param data   wx.getUserInfo 接口返回参数
     *               rawData,signature,encryptedData,iv 为必须传入参数;其他为可选
     * @return ResponseDTO
     * @throws Exception
     */
    ResponseDTO<MiniUserInfo> authority(String sysId, String jscode, MiniAppAuthData data) throws Exception;

    /**
     * 微信绑定
     *
     * @param data 发送数据
     * @return ResponseDTO
     */
    ResponseDTO bindWechat(MapWechatSys data);
}
