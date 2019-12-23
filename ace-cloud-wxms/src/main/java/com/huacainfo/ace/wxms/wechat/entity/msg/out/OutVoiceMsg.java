/**
 * project name:distribution
 * file name:OutVoiceMsg
 * package name:com.arvin.wechat.pojo.msg.out
 * date:2016-07-27 11:55
 * author:Administrator
 * Copyright (c) CD Technology Co.,Ltd. All rights reserved.
 */
package com.huacainfo.ace.wxms.wechat.entity.msg.out;

/**
 * date: 2016-07-27 11:55
 *
 * @author Arvin
 * @version 1.0
 * @since JDK 1.8
 */


import com.huacainfo.ace.wxms.wechat.entity.msg.in.InMsg;

/**
 * 回复语音消息
 * <xml>
 * <ToUserName><![CDATA[toUser]]></ToUserName>
 * <FromUserName><![CDATA[fromUser]]></FromUserName>
 * <CreateTime>12345678</CreateTime>
 * <MsgType><![CDATA[voice]]></MsgType>
 * <Voice>
 * <MediaId><![CDATA[media_id]]></MediaId>
 * </Voice>
 * </xml>
 */
public class OutVoiceMsg extends OutMsg {
    public static final String TEMPLATE =
            "<xml>\n" +
                    "<ToUserName><![CDATA[${__msg.toUserName}]]></ToUserName>\n" +
                    "<FromUserName><![CDATA[${__msg.fromUserName}]]></FromUserName>\n" +
                    "<CreateTime>${__msg.createTime}</CreateTime>\n" +
                    "<MsgType><![CDATA[${__msg.msgType}]]></MsgType>\n" +
                    "<Voice>\n" +
                    "<MediaId><![CDATA[${__msg.mediaId}]]></MediaId>\n" +
                    "</Voice>\n" +
                    "</xml>";

    private String mediaId;

    public OutVoiceMsg() {
        this.msgType = "voice";
    }

    public OutVoiceMsg(InMsg inMsg) {
        super(inMsg);
        this.msgType = "voice";
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
}
