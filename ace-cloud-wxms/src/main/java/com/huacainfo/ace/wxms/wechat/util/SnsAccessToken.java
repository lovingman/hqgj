/**
 * project name:distribution
 * file name:SnsAccessToken
 * package name:com.arvin.wechat.util
 * date:2016/8/8 15:55
 * author:haing
 * Copyright (c) CD Technology Co.,Ltd. All rights reserved.
 */
package com.huacainfo.ace.wxms.wechat.util;


import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.wxms.wechat.constant.ReturnCode;

import java.io.Serializable;
import java.util.Map;

/**
 * description: //sns获取授权对象 <br>
 * date: 2016/8/8 15:55
 *
 * @author haing
 * @version 1.0
 * @since JDK 1.8
 */
public class SnsAccessToken implements RetryUtils.ResultCheck, Serializable {
    private static final long serialVersionUID = 6369625123403343963L;
    private String access_token;
    private Integer expires_in;
    private String refresh_token;
    private String openid;
    private String scope;
    private String unionid;
    private Integer errcode;
    private String errmsg;
    private Long expiredTime;
    private String json;

    public SnsAccessToken(String jsonStr) {
        this.json = jsonStr;

        try {
            Map e = JsonUtil.toObject(jsonStr, Map.class);
            this.access_token = (String) e.get("access_token");
            this.expires_in = this.getInt(e, "expires_in");
            this.refresh_token = (String) e.get("refresh_token");
            this.openid = (String) e.get("openid");
            this.unionid = (String) e.get("unionid");
            this.scope = (String) e.get("scope");
            this.errcode = this.getInt(e, "errcode");
            this.errmsg = (String) e.get("errmsg");
            if (this.expires_in != null) {
                this.expiredTime = Long.valueOf(System.currentTimeMillis() + (long) ((this.expires_in.intValue() - 5) * 1000));
            }

        } catch (Exception var3) {
            throw new RuntimeException(var3);
        }
    }

    @Override
    public String getJson() {
        return this.json;
    }

    private Integer getInt(Map<String, Object> temp, String key) {
        Number number = (Number) temp.get(key);
        return number == null ? null : Integer.valueOf(number.intValue());
    }

    public boolean isAvailable() {
        return this.expiredTime == null ? false : (this.errcode != null ? false : (this.expiredTime.longValue() < System.currentTimeMillis() ? false : this.access_token != null));
    }

    public String getAccessToken() {
        return this.access_token;
    }

    public Integer getExpiresIn() {
        return this.expires_in;
    }

    public String getRefresh_token() {
        return this.refresh_token;
    }

    public String getOpenid() {
        return this.openid;
    }

    public String getScope() {
        return this.scope;
    }

    public Integer getErrorCode() {
        return this.errcode;
    }

    public String getErrorMsg() {
        if (this.errcode != null) {
            String result = ReturnCode.get(this.errcode.intValue());
            if (result != null) {
                return result;
            }
        }
        return this.errmsg;
    }

    public String getUnionid() {
        return this.unionid;
    }

    @Override
    public boolean matching() {
        return this.isAvailable();
    }


}
