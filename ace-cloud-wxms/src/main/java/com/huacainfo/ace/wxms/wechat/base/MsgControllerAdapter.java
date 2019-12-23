/**
 * project name:distribution
 * file name:MsgControllerAdapter
 * package name:com.arvin.wechat.controller.base
 * date:2016-07-27 12:04
 * author:Administrator
 * Copyright (c) CD Technology Co.,Ltd. All rights reserved.
 */
package com.huacainfo.ace.wxms.wechat.base;


import com.huacainfo.ace.wxms.wechat.entity.msg.in.*;
import com.huacainfo.ace.wxms.wechat.entity.msg.in.event.*;
import com.huacainfo.ace.wxms.wechat.entity.msg.in.speech_recognition.InSpeechRecognitionResults;

/**
 * description:
 * MsgControllerAdapter 对 MsgController 部分方法提供了默认实现，
 * 以便开发者不去关注 MsgController 中不需要处理的抽象方法，节省代码量
 * date: 2016-07-27 12:04
 *
 * @author Administrator
 * @version 1.0
 * @since JDK 1.8
 */
public abstract class MsgControllerAdapter extends WxMsgController {

    // 处理接收到的关注/取消关注事件
    protected abstract String processInFollowEvent(InFollowEvent inFollowEvent);

    // 处理接收到的文本消息
    protected abstract String processInTextMsg(InTextMsg inTextMsg);

    // 处理接收到的自定义菜单事件
    protected abstract String processInMenuEvent(InMenuEvent inMenuEvent);

    @Override
    protected String processInQrCodeEvent(InQrCodeEvent inQrCodeEvent) {
        return "";
    }

    @Override
    protected String processInLocationEvent(InLocationEvent inLocationEvent) {
        return "";
    }

    @Override
    protected String processInMassEvent(InMassEvent inMassEvent) {
        return "";
    }

    @Override
    protected String processInImageMsg(InImageMsg inImageMsg) {
        return "";
    }

    @Override
    protected String processInVoiceMsg(InVoiceMsg inVoiceMsg) {
        return "";
    }

    @Override
    protected String processInVideoMsg(InVideoMsg inVideoMsg) {
        return "";
    }

    @Override
    protected String processInShortVideoMsg(InShortVideoMsg inShortVideoMsg) {
        return "";
    }

    @Override
    protected String processInLocationMsg(InLocationMsg inLocationMsg) {
        return "";
    }

    @Override
    protected String processInLinkMsg(InLinkMsg inLinkMsg) {
        return "";
    }

    @Override
    protected String processInCustomEvent(InCustomEvent inCustomEvent) {
        return "";
    }

    @Override
    protected String processInSpeechRecognitionResults(InSpeechRecognitionResults inSpeechRecognitionResults) {
        return "";
    }

    @Override
    protected String processInTemplateMsgEvent(InTemplateMsgEvent inTemplateMsgEvent) {
        return "";
    }

    @Override
    protected String processInShakearoundUserShakeEvent(InShakearoundUserShakeEvent inShakearoundUserShakeEvent) {
        return "";
    }

    @Override
    protected String processInVerifySuccessEvent(InVerifySuccessEvent inVerifySuccessEvent) {
        return "";
    }

    @Override
    protected String processInVerifyFailEvent(InVerifyFailEvent inVerifyFailEvent) {
        return "";
    }

    @Override
    protected String processInPoiCheckNotifyEvent(InPoiCheckNotifyEvent inPoiCheckNotifyEvent) {
        return "";
    }


}
