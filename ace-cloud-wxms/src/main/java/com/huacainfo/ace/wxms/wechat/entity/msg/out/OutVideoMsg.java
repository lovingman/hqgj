/**
 * project name:distribution
 * file name:OutVideoMsg
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
 * 回复视频消息
 * <xml>
 * <ToUserName><![CDATA[toUser]]></ToUserName>
 * <FromUserName><![CDATA[fromUser]]></FromUserName>
 * <CreateTime>12345678</CreateTime>
 * <MsgType><![CDATA[video]]></MsgType>
 * <Video>
 * <MediaId><![CDATA[media_id]]></MediaId>
 * <Title><![CDATA[title]]></Title>
 * <Description><![CDATA[description]]></Description>
 * </Video>
 * </xml>
 */
public class OutVideoMsg extends OutMsg {
    public static final String TEMPLATE =
            "<xml>\n" +
                    "<ToUserName><![CDATA[${__msg.toUserName}]]></ToUserName>\n" +
                    "<FromUserName><![CDATA[${__msg.fromUserName}]]></FromUserName>\n" +
                    "<CreateTime>${__msg.createTime}</CreateTime>\n" +
                    "<MsgType><![CDATA[${__msg.msgType}]]></MsgType>\n" +
                    "<Video>\n" +
                    "<MediaId><![CDATA[${__msg.mediaId}]]></MediaId>\n" +
                    "<Title><![CDATA[${(__msg.title)!}]]></Title>\n" +
                    "<Description><![CDATA[${(__msg.description)!}]]></Description>\n" +
                    "</Video>\n" +
                    "</xml>";

    private String mediaId;
    private String title;        // 不是必须
    private String description;    // 不是必须

    public OutVideoMsg() {
        this.msgType = "video";
    }

    public OutVideoMsg(InMsg inMsg) {
        super(inMsg);
        this.msgType = "video";
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
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
}
