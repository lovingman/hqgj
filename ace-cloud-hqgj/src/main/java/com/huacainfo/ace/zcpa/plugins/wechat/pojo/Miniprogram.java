package com.huacainfo.ace.zcpa.plugins.wechat.pojo;

import java.io.Serializable;

/**
 * Created by HuaCai008 on 2018/4/16.
 */
public class Miniprogram implements Serializable {
    private String appid;
    private String pagepath;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPagepath() {
        return pagepath;
    }

    public void setPagepath(String pagepath) {
        this.pagepath = pagepath;
    }
}
