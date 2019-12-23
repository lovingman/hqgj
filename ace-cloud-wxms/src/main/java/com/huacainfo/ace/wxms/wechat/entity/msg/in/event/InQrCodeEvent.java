/**
 * project name:distribution
 * file name:InQrCodeEvent
 * package name:com.arvin.wechat.pojo.msg.in.event
 * date:2016-07-27 11:12
 * author:Administrator
 * Copyright (c) CD Technology Co.,Ltd. All rights reserved.
 */
package com.huacainfo.ace.wxms.wechat.entity.msg.in.event;

/**
 * date: 2016-07-27 11:12
 *
 * @author Arvin
 * @version 1.0
 * @since JDK 1.8
 */

/**
 * 扫描带参数二维码事件
 * 1. 用户未关注时，进行关注后的事件推送
 * <xml>
 * <ToUserName><![CDATA[toUser]]></ToUserName>
 * <FromUserName><![CDATA[FromUser]]></FromUserName>
 * <CreateTime>123456789</CreateTime>
 * <MsgType><![CDATA[event]]></MsgType>
 * <Event><![CDATA[subscribe]]></Event>
 * <EventKey><![CDATA[qrscene_123123]]></EventKey>
 * <Ticket><![CDATA[TICKET]]></Ticket>
 * </xml>
 * <p>
 * 2. 用户已关注时的事件推送
 * <xml>
 * <ToUserName><![CDATA[toUser]]></ToUserName>
 * <FromUserName><![CDATA[FromUser]]></FromUserName>
 * <CreateTime>123456789</CreateTime>
 * <MsgType><![CDATA[event]]></MsgType>
 * <Event><![CDATA[SCAN]]></Event>
 * <EventKey><![CDATA[SCENE_VALUE]]></EventKey>
 * <Ticket><![CDATA[TICKET]]></Ticket>
 * </xml>
 */
public class InQrCodeEvent extends EventInMsg {

    // 1. 用户未关注时，进行关注后的事件推送： subscribe
    public static final String EVENT_INQRCODE_SUBSCRIBE = "subscribe";
    // 2. 用户已关注时的事件推送： SCAN
    public static final String EVENT_INQRCODE_SCAN = "SCAN";

    // 1. 用户未关注时，进行关注后的事件推送： qrscene_123123
    // 2. 用户已关注时的事件推送： SCENE_VALUE
    private String eventKey;
    private String ticket;

    public InQrCodeEvent(String toUserName, String fromUserName, Integer createTime, String msgType, String event) {
        super(toUserName, fromUserName, createTime, msgType, event);
    }

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
}
