/**
 * project name:distribution
 * file name:InVerifyFailEvent
 * package name:com.arvin.wechat.pojo.msg.in.event
 * date:2016-07-27 11:27
 * author:Administrator
 * Copyright (c) CD Technology Co.,Ltd. All rights reserved.
 */
package com.huacainfo.ace.wxms.wechat.entity.msg.in.event;

/**
 * date: 2016-07-27 11:27
 *
 * @author Arvin
 * @version 1.0
 * @since JDK 1.8
 */

/**
 * <xml><ToUserName><![CDATA[toUser]]></ToUserName>
 * <FromUserName><![CDATA[FromUser]]></FromUserName>
 * <CreateTime>1442401061</CreateTime>
 * <MsgType><![CDATA[event]]></MsgType>
 * <Event><![CDATA[naming_verify_fail]]></Event>
 * <FailTime>1442401061</FailTime>
 * <FailReason><![CDATA[by time]]></FailReason>
 * </xml>
 */
public class InVerifyFailEvent extends EventInMsg {
    //资质认证失败
    public static final String EVENT_IN_QUALIFICATION_VERIFY_FAIL = "qualification_verify_fail";
    //名称认证失败
    public static final String EVENT_IN_NAMING_VERIFY_FAIL = "naming_verify_fail";

    private String failTime;
    private String failReason;

    public InVerifyFailEvent(String toUserName, String fromUserName, Integer createTime, String msgType, String event) {
        super(toUserName, fromUserName, createTime, msgType, event);
    }

    public String getFailTime() {
        return failTime;
    }

    public void setFailTime(String failTime) {
        this.failTime = failTime;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }
}
