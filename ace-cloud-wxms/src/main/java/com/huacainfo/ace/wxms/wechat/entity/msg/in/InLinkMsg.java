/**
 * project name:distribution
 * file name:InLinkMsg
 * package name:com.arvin.wechat.pojo.msg.in
 * date:2016-07-27 11:08
 * author:Administrator
 * Copyright (c) CD Technology Co.,Ltd. All rights reserved.
 */
package com.huacainfo.ace.wxms.wechat.entity.msg.in;

/**
 * date: 2016-07-27 11:08
 *
 * @author Administrator
 * @version 1.0
 * @since JDK 1.8
 */
public class InLinkMsg extends InMsg {
    /**
     * 接收链接消息
     * <xml>
     * <ToUserName><![CDATA[toUser]]></ToUserName>
     * <FromUserName><![CDATA[fromUser]]></FromUserName>
     * <CreateTime>1351776360</CreateTime>
     * <MsgType><![CDATA[link]]></MsgType>
     * <Title><![CDATA[公众平台官网链接]]></Title>
     * <Description><![CDATA[公众平台官网链接]]></Description>
     * <Url><![CDATA[url]]></Url>
     * <MsgId>1234567890123456</MsgId>
     * </xml>
     */
    private String title;
    private String description;
    private String url;
    private String msgId;

    public InLinkMsg(String toUserName, String fromUserName, Integer createTime, String msgType) {
        super(toUserName, fromUserName, createTime, msgType);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
}
