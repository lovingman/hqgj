/**
 * project name:distribution
 * file name:InTextMsg
 * package name:com.arvin.wechat.pojo.msg.in
 * date:2016-07-27 11:00
 * author:Administrator
 * Copyright (c) CD Technology Co.,Ltd. All rights reserved.
 */
package com.huacainfo.ace.wxms.wechat.entity.msg.in;

/**
 * description:
 * 接收文本消息
 * <xml>
 * <ToUserName><![CDATA[toUser]]></ToUserName>
 * <FromUserName><![CDATA[fromUser]]></FromUserName>
 * <CreateTime>1348831860</CreateTime>
 * <MsgType><![CDATA[text]]></MsgType>
 * <Content><![CDATA[this is a test]]></Content>
 * <MsgId>1234567890123456</MsgId>
 * </xml>
 * date: 2016-07-27 11:00
 *
 * @author Arvin
 * @version 1.0
 * @since JDK 1.8
 */
public class InTextMsg extends InMsg {
    private String content;
    private String msgId;

    public InTextMsg(String toUserName, String fromUserName, Integer createTime, String msgType) {
        super(toUserName, fromUserName, createTime, msgType);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
}
