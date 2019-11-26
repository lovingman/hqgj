package com.huacainfo.ace.zcpa.plugins.wechat.pojo.resp;

import com.huacainfo.ace.common.model.BaseModel;

/**
 * @ClassName AccessTokenResp
 * @Description 微信令牌接口返回接收类
 * @Author Arvin Zou
 * @Date 2019/9/17 11:47
 */
public class AccessTokenResp extends BaseModel {

    /**
     * 获取到的凭证
     */
    private String access_token;
    /**
     * 凭证有效时间，单位：秒
     */
    private int expires_in;
    /**
     * -1	系统繁忙，此时请开发者稍候再试
     * 0	请求成功
     * 40001	AppSecret错误或者AppSecret不属于这个公众号，请开发者确认AppSecret的正确性
     * 40002	请确保grant_type字段值为client_credential
     * 40164	调用接口的IP地址不在白名单中，请在接口IP白名单中进行设置。（小程序及小游戏调用不要求IP地址在白名单内。）
     */
    private int errcode;
    /**
     * 错误信息
     */
    private String errmsg;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
