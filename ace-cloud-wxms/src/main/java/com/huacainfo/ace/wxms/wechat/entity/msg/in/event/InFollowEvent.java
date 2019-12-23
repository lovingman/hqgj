/**
 * project name:distribution
 * file name:InFollowEvent
 * package name:com.arvin.wechat.pojo.msg.in.event
 * date:2016-07-27 11:11
 * author:Administrator
 * Copyright (c) CD Technology Co.,Ltd. All rights reserved.
 */
package com.huacainfo.ace.wxms.wechat.entity.msg.in.event;

/**
 * date: 2016-07-27 11:11
 *
 * @author Arvin
 * @version 1.0
 * @since JDK 1.8
 * <p>
 * 接收 关注/取消关注事件
 * <xml>
 * <ToUserName><![CDATA[toUser]]></ToUserName>
 * <FromUserName><![CDATA[FromUser]]></FromUserName>
 * <CreateTime>123456789</CreateTime>
 * <MsgType><![CDATA[event]]></MsgType>
 * <Event><![CDATA[subscribe]]></Event>
 * </xml>
 * <p>
 * 接收 关注/取消关注事件
 * <xml>
 * <ToUserName><![CDATA[toUser]]></ToUserName>
 * <FromUserName><![CDATA[FromUser]]></FromUserName>
 * <CreateTime>123456789</CreateTime>
 * <MsgType><![CDATA[event]]></MsgType>
 * <Event><![CDATA[subscribe]]></Event>
 * </xml>
 * <p>
 * 接收 关注/取消关注事件
 * <xml>
 * <ToUserName><![CDATA[toUser]]></ToUserName>
 * <FromUserName><![CDATA[FromUser]]></FromUserName>
 * <CreateTime>123456789</CreateTime>
 * <MsgType><![CDATA[event]]></MsgType>
 * <Event><![CDATA[subscribe]]></Event>
 * </xml>
 */
/**
 * 接收 关注/取消关注事件
 * <xml>
 * <ToUserName><![CDATA[toUser]]></ToUserName>
 * <FromUserName><![CDATA[FromUser]]></FromUserName>
 * <CreateTime>123456789</CreateTime>
 * <MsgType><![CDATA[event]]></MsgType>
 * <Event><![CDATA[subscribe]]></Event>
 * </xml>
 */

/**
 关注实测数据结果： 比官方文档多出一个 EventKey 标记
 <xml>
 <ToUserName><![CDATA[gh_e21b62f685f4]]></ToUserName>
 <FromUserName><![CDATA[o5Ljujn78A_S0uk_WvAM_fKFEXm4]]></FromUserName>
 <CreateTime>1411785252</CreateTime>
 <MsgType><![CDATA[event]]></MsgType>
 <Event><![CDATA[subscribe]]></Event>
 <EventKey><![CDATA[]]></EventKey>
 </xml>
 取消关注实测数据结果：比官方文档多出一个 EventKey 标记
 <xml>
 <ToUserName><![CDATA[gh_e21b62f685f4]]></ToUserName>
 <FromUserName><![CDATA[o5Ljujn78A_S0uk_WvAM_fKFEXm4]]></FromUserName>
 <CreateTime>1411785559</CreateTime>
 <MsgType><![CDATA[event]]></MsgType>
 <Event><![CDATA[unsubscribe]]></Event>
 <EventKey><![CDATA[]]></EventKey>
 </xml>
 */
public class InFollowEvent extends EventInMsg {
    // 订阅：subscribe
    public static final String EVENT_INFOLLOW_SUBSCRIBE = "subscribe";
    // 取消订阅：unsubscribe
    public static final String EVENT_INFOLLOW_UNSUBSCRIBE = "unsubscribe";

    public InFollowEvent(String toUserName, String fromUserName, Integer createTime, String msgType, String event) {
        super(toUserName, fromUserName, createTime, msgType, event);
    }
}
