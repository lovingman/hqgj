/**
 * project name:distribution
 * file name:InImageMsg
 * package name:com.arvin.wechat.pojo.msg.in
 * date:2016-07-27 11:01
 * author:Administrator
 * Copyright (c) CD Technology Co.,Ltd. All rights reserved.
 */
package com.huacainfo.ace.wxms.wechat.entity.msg.in;

/**
 * date: 2016-07-27 11:01
 *
 * @author Arvin
 * @version 1.0
 * @since JDK 1.8
 */
public class InImageMsg extends InMsg {
    /**
     * 接收图片消息
     * <xml>
     * <ToUserName><![CDATA[toUser]]></ToUserName>
     * <FromUserName><![CDATA[fromUser]]></FromUserName>
     * <CreateTime>1348831860</CreateTime>
     * <MsgType><![CDATA[image]]></MsgType>
     * <PicUrl><![CDATA[this is a url]]></PicUrl>
     * <MediaId><![CDATA[media_id]]></MediaId>
     * <MsgId>1234567890123456</MsgId>
     * </xml>
     */

    private String picUrl;
    private String mediaId;
    private String msgId;

    public InImageMsg(String toUserName, String fromUserName, Integer createTime, String msgType) {
        super(toUserName, fromUserName, createTime, msgType);
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
}
