package com.huacainfo.ace.wxms.wechat.entity.pojo;

import com.huacainfo.ace.common.model.BaseModel;

/**
 * @ClassName MiniAppAuthData
 * @Description 微信小程序获取用户信息: 用于解密用户信息时,接收 wx.getUserInfo 接口返回参数
 * @Author Arvin Zou
 * @Date 2019/10/8 14:38
 */
public class MiniAppAuthData extends BaseModel {

    /**
     * 不包括敏感信息的原始数据字符串，用于计算签名
     */
    private String rawData;
    /**
     * 使用 sha1( rawData + sessionkey ) 得到字符串，用于校验用户信息，详见 用户数据的签名验证和加解密
     */
    private String signature;
    /**
     * 包括敏感数据在内的完整用户信息的加密数据，详见 用户数据的签名验证和加解密
     */
    private String encryptedData;
    /**
     * 加密算法的初始向量，详见 用户数据的签名验证和加解密
     */
    private String iv;
    /**
     * 敏感数据对应的云 ID，开通云开发的小程序才会返回，可通过云调用直接获取开放数据，详细见云调用直接获取开放数据
     */
    private String cloudID;


    public String getRawData() {
        return rawData;
    }

    public void setRawData(String rawData) {
        this.rawData = rawData;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getEncryptedData() {
        return encryptedData;
    }

    public void setEncryptedData(String encryptedData) {
        this.encryptedData = encryptedData;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }

    public String getCloudID() {
        return cloudID;
    }

    public void setCloudID(String cloudID) {
        this.cloudID = cloudID;
    }
}
