package com.huacainfo.ace.wxms.wechat.entity;

import java.io.Serializable;

/**
 * @author Arvin
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
