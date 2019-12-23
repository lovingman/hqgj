/**
 * project name:distribution
 * file name:InCustomEvent
 * package name:com.arvin.wechat.pojo.msg.in
 * date:2016-07-27 11:09
 * author:Administrator
 * Copyright (c) CD Technology Co.,Ltd. All rights reserved.
 */
package com.huacainfo.ace.wxms.wechat.entity.msg.in.event;

/**
 * date: 2016-07-27 11:09
 *
 * @author Arvin
 * @version 1.0
 * @since JDK 1.8
 */
public class InCustomEvent extends EventInMsg {

    // 接入会话：kf_create_session
    public static final String EVENT_INCUSTOM_KF_CREATE_SESSION = "kf_create_session";
    // 关闭会话：kf_close_session
    public static final String EVENT_INCUSTOM_KF_CLOSE_SESSION = "kf_close_session";
    // 转接会话：kf_switch_session
    public static final String EVENT_INCUSTOM_KF_SWITCH_SESSION = "kf_switch_session";

    private String kfAccount;
    private String toKfAccount;

    public InCustomEvent(String toUserName, String fromUserName, Integer createTime, String msgType, String event) {
        super(toUserName, fromUserName, createTime, msgType, event);
    }

    public String getKfAccount() {
        return kfAccount;
    }

    public void setKfAccount(String kfAccount) {
        this.kfAccount = kfAccount;
    }

    public String getToKfAccount() {
        return toKfAccount;
    }

    public void setToKfAccount(String toKfAccount) {
        this.toKfAccount = toKfAccount;
    }
}
