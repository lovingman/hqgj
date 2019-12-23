/**
 * project name:distribution
 * file name:InSpeechRecognitionResults
 * package name:com.arvin.wechat.pojo.msg.in.speech_recognition
 * date:2016-07-27 11:21
 * author:Administrator
 * Copyright (c) CD Technology Co.,Ltd. All rights reserved.
 */
package com.huacainfo.ace.wxms.wechat.entity.msg.in.speech_recognition;


/**
 * date: 2016-07-27 11:21
 *
 * @author Arvin
 * @version 1.0
 * @since JDK 1.8
 */


import com.huacainfo.ace.wxms.wechat.entity.msg.in.InVoiceMsg;

/**
 * 接收语音识别结果，与 InVoiceMsg 唯一的不同是多了一个 Recognition 标记
 * <xml>
 * <ToUserName><![CDATA[toUser]]></ToUserName>
 * <FromUserName><![CDATA[fromUser]]></FromUserName>
 * <CreateTime>1357290913</CreateTime>
 * <MsgType><![CDATA[voice]]></MsgType>
 * <MediaId><![CDATA[media_id]]></MediaId>
 * <Format><![CDATA[Format]]></Format>
 * <Recognition><![CDATA[腾讯微信团队]]></Recognition>
 * <MsgId>1234567890123456</MsgId>
 * </xml>
 */
public class InSpeechRecognitionResults extends InVoiceMsg {

    private String recognition;

    public InSpeechRecognitionResults(String toUserName, String fromUserName, Integer createTime, String msgType) {
        super(toUserName, fromUserName, createTime, msgType);
    }

    public String getRecognition() {
        return recognition;
    }

    public void setRecognition(String recognition) {
        this.recognition = recognition;
    }

}
