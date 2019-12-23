/**
 * project name:distribution
 * file name:EventInMsg
 * package name:com.arvin.wechat.pojo.msg.in.event
 * date:2016-07-27 11:11
 * author:Administrator
 * Copyright (c) CD Technology Co.,Ltd. All rights reserved.
 */
package com.huacainfo.ace.wxms.wechat.entity.msg.in.event;


import com.huacainfo.ace.wxms.wechat.entity.msg.in.InMsg;

/**
 * date: 2016-07-27 11:11
 *
 * @author Arvin
 * @version 1.0
 * @since JDK 1.8
 */
public abstract class EventInMsg extends InMsg {
    protected String event;

    public EventInMsg(String toUserName, String fromUserName, Integer createTime, String msgType, String event) {
        super(toUserName, fromUserName, createTime, msgType);
        this.event = event;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}
