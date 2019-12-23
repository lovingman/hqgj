package com.huacainfo.ace.wxms.wechat.entity.pojo.resp;

import com.huacainfo.ace.common.model.BaseModel;

/**
 * @ClassName Code2SessionResp
 * @Description auth.code2Session 接口返回封装类
 * @Author Arvin Zou
 * @Date 2019/10/8 15:26
 */
public class Code2SessionResp extends BaseModel {

    /**
     * string	用户唯一标识
     */
    private String openid;
    /**
     * string	会话密钥
     */
    private String session_key;
    /**
     * string	用户在开放平台的唯一标识符，在满足 UnionID 下发条件的情况下会返回，详见 UnionID 机制说明。
     */
    private String unionid;
    /**
     * number	错误码
     */
    private int errcode;
    /**
     * string	错误信息
     */
    private String errmsg;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
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
