/**
 * project name:distribution
 * file name:InVoiceMsg
 * package name:com.arvin.wechat.pojo.msg.in
 * date:2016-07-27 11:03
 * author:Administrator
 * Copyright (c) CD Technology Co.,Ltd. All rights reserved.
 */
package com.huacainfo.ace.wxms.wechat.entity.msg.in;

/**
 * date: 2016-07-27 11:03
 *
 * @author Arvin
 * @version 1.0
 * @since JDK 1.8
 */
public class InVoiceMsg extends InMsg {
    /**
     * 接收语音消息
     * <xml>
     * <ToUserName><![CDATA[toUser]]></ToUserName>
     * <FromUserName><![CDATA[fromUser]]></FromUserName>
     * <CreateTime>1357290913</CreateTime>
     * <MsgType><![CDATA[voice]]></MsgType>
     * <MediaId><![CDATA[media_id]]></MediaId>
     * <Format><![CDATA[Format]]></Format>
     * <MsgId>1234567890123456</MsgId>
     * </xml>
     */
    private String mediaId;
    private String format;
    private String msgId;

    public InVoiceMsg(String toUserName, String fromUserName, Integer createTime, String msgType) {
        super(toUserName, fromUserName, createTime, msgType);
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
}
