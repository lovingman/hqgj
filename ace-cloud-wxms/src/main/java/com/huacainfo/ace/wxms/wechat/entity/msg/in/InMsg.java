/**
 * project name:distribution
 * file name:InMsg
 * package name:com.arvin.wechat.pojo.msg.in
 * date:2016-07-27 10:59
 * author:Administrator
 * Copyright (c) CD Technology Co.,Ltd. All rights reserved.
 */
package com.huacainfo.ace.wxms.wechat.entity.msg.in;


import com.huacainfo.ace.common.tools.JsonUtil;

import java.io.Serializable;

/**
 * description:
 * 接收消息，以下是接收文本消息的例子
 * 接收文本消息
 * <xml>
 * <ToUserName><![CDATA[toUser]]></ToUserName>
 * <FromUserName><![CDATA[fromUser]]></FromUserName>
 * <CreateTime>1348831860</CreateTime>
 * <MsgType><![CDATA[text]]></MsgType>
 * <Content><![CDATA[this is a test]]></Content>
 * <MsgId>1234567890123456</MsgId>
 * </xml>
 * date: 2016-07-27 10:59
 *
 * @author Arvin
 * @version 1.0
 * @since JDK 1.8
 */
public abstract class InMsg implements Serializable {
    private static final long serialVersionUID = 1L;

    // 开发者微信号
    protected String toUserName;

    // 发送方帐号（一个OpenID）
    protected String fromUserName;

    // 消息创建时间 （整型）
    protected Integer createTime;

    /**
     * 消息类型
     * 1：text 文本消息
     * 2：image 图片消息
     * 3：voice 语音消息
     * 4：video 视频消息
     * 5：location 地址位置消息
     * 6：link 链接消息
     * 7：event 事件
     */
    protected String msgType;

    public InMsg(String toUserName, String fromUserName, Integer createTime, String msgType) {
        this.toUserName = toUserName;
        this.fromUserName = fromUserName;
        this.createTime = createTime;
        this.msgType = msgType;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
}
