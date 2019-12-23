/**
 * project name:distribution
 * file name:InShortVideoMsg
 * package name:com.arvin.wechat.pojo.msg.in
 * date:2016-07-27 11:06
 * author:Administrator
 * Copyright (c) CD Technology Co.,Ltd. All rights reserved.
 */
package com.huacainfo.ace.wxms.wechat.entity.msg.in;

/**
 * date: 2016-07-27 11:06
 *
 * @author Arvin
 * @version 1.0
 * @since JDK 1.8
 */
public class InShortVideoMsg extends InMsg {

    /**
     * 接收视频消息
     * <xml>
     * <ToUserName><![CDATA[toUser]]></ToUserName>
     * <FromUserName><![CDATA[fromUser]]></FromUserName>
     * <CreateTime>1357290913</CreateTime>
     * <MsgType><![CDATA[shortvideo]]></MsgType>
     * <MediaId><![CDATA[media_id]]></MediaId>
     * <ThumbMediaId><![CDATA[thumb_media_id]]></ThumbMediaId>
     * <MsgId>1234567890123456</MsgId>
     * </xml>
     */
    private String mediaId;
    private String thumbMediaId;
    private String msgId;

    public InShortVideoMsg(String toUserName, String fromUserName, Integer createTime, String msgType) {
        super(toUserName, fromUserName, createTime, msgType);
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
}
