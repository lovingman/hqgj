/**
 * project name:distribution
 * file name:WxMsgController
 * package name:com.arvin.wechat.controller.base
 * date:2016-07-27 10:52
 * author:Administrator
 * Copyright (c) CD Technology Co.,Ltd. All rights reserved.
 */
package com.huacainfo.ace.wxms.wechat.base;


import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.wxms.wechat.entity.msg.InMsgParser;
import com.huacainfo.ace.wxms.wechat.entity.msg.OutMsgXmlBuilder;
import com.huacainfo.ace.wxms.wechat.entity.msg.in.*;
import com.huacainfo.ace.wxms.wechat.entity.msg.in.event.*;
import com.huacainfo.ace.wxms.wechat.entity.msg.in.speech_recognition.InSpeechRecognitionResults;
import com.huacainfo.ace.wxms.wechat.entity.msg.out.OutMsg;
import com.huacainfo.ace.wxms.wechat.entity.msg.out.OutTextMsg;
import com.huacainfo.ace.wxms.wechat.util.HttpKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * description: 接收微信服务器消息，自动解析成 InMsg 并分发到相应的处理方法
 * date: 2016-07-27 10:52
 *
 * @author Arvin
 * @version 1.0
 * @since JDK 1.8
 */
public abstract class WxMsgController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    private String inMsgXml = null;        // 本次请求 xml数据
    private InMsg inMsg = null;            // 本次请求 xml 解析后的 InMsg 对象

    private HttpServletRequest request;
    private HttpServletResponse response;

    public HttpServletRequest getRequest() {
        return request;
    }

//    public void setRequest(HttpServletRequest request) {
//        this.request = request;
//    }

    public HttpServletResponse getResponse() {
        return response;
    }

//    public void setResponse(HttpServletResponse response) {
//        this.response = response;
//    }

    /**
     * weixin 公众号服务器调用唯一入口，即在开发者中心输入的 URL 必须要指向此 action
     */

    public String index(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.inMsgXml = null; // 本次请求 xml数据
        this.inMsg = null; // 本次请求 xml 解析后的 InMsg 对象

        // 解析消息并根据消息类型分发到相应的处理方法
        InMsg msg = getInMsg();
        logger.debug("msg={}", JsonUtil.toJson(msg));
        if (msg instanceof InTextMsg)
            return processInTextMsg((InTextMsg) msg);
        else if (msg instanceof InImageMsg)
            return processInImageMsg((InImageMsg) msg);
        else if (msg instanceof InVoiceMsg)
            return processInVoiceMsg((InVoiceMsg) msg);
        else if (msg instanceof InVideoMsg)
            return processInVideoMsg((InVideoMsg) msg);
        else if (msg instanceof InShortVideoMsg)   //支持小视频
            return processInShortVideoMsg((InShortVideoMsg) msg);
        else if (msg instanceof InLocationMsg)
            return processInLocationMsg((InLocationMsg) msg);
        else if (msg instanceof InLinkMsg)
            return processInLinkMsg((InLinkMsg) msg);
        else if (msg instanceof InCustomEvent)
            return processInCustomEvent((InCustomEvent) msg);
        else if (msg instanceof InFollowEvent)
            return processInFollowEvent((InFollowEvent) msg);
        else if (msg instanceof InQrCodeEvent)
            return processInQrCodeEvent((InQrCodeEvent) msg);
        else if (msg instanceof InLocationEvent)
            return processInLocationEvent((InLocationEvent) msg);
        else if (msg instanceof InMassEvent)
            return processInMassEvent((InMassEvent) msg);
        else if (msg instanceof InMenuEvent)
            return processInMenuEvent((InMenuEvent) msg);
        else if (msg instanceof InSpeechRecognitionResults)
            return processInSpeechRecognitionResults((InSpeechRecognitionResults) msg);
        else if (msg instanceof InTemplateMsgEvent)
            return processInTemplateMsgEvent((InTemplateMsgEvent) msg);
        else if (msg instanceof InShakearoundUserShakeEvent)
            return processInShakearoundUserShakeEvent((InShakearoundUserShakeEvent) msg);
        else if (msg instanceof InVerifySuccessEvent)
            return processInVerifySuccessEvent((InVerifySuccessEvent) msg);
        else if (msg instanceof InVerifyFailEvent)
            return processInVerifyFailEvent((InVerifyFailEvent) msg);
        else if (msg instanceof InPoiCheckNotifyEvent)
            return processInPoiCheckNotifyEvent((InPoiCheckNotifyEvent) msg);
        else
            System.out.println("未能识别的消息类型。 消息 xml 内容为：\n" + getInMsgXml());

        return "ERROR_MSG_TYPE";
    }

    /**
     * 在接收到微信服务器的 InMsg 消息后后响应 OutMsg 消息
     */
    public String render(OutMsg outMsg) {
        String outMsgXml = OutMsgXmlBuilder.build(outMsg);

//        // 是否需要加密消息
//        if (ApiConfigKit.getApiConfig().isEncryptMessage()) {
//            outMsgXml = MsgEncryptKit.encrypt(outMsgXml, getPara("timestamp"), getPara("nonce"));
//        }

        return outMsgXml;
    }

    public String renderOutTextMsg(String content) {
        OutTextMsg outMsg = new OutTextMsg(getInMsg());
        outMsg.setContent(content);
        return render(outMsg);
    }

    public String getInMsgXml() {
        if (inMsgXml == null) {
            inMsgXml = HttpKit.readData(getRequest());

//            // 是否需要解密消息
//            if (ApiConfigKit.getApiConfig().isEncryptMessage()) {
//                inMsgXml = MsgEncryptKit.decrypt(inMsgXml, getPara("timestamp"), getPara("nonce"), getPara("msg_signature"));
//            }
        }
        return inMsgXml;
    }

    public InMsg getInMsg() {
        if (inMsg == null)
            inMsg = InMsgParser.parse(getInMsgXml());
        return inMsg;
    }

    // 处理接收到的文本消息
    protected abstract String processInTextMsg(InTextMsg inTextMsg);

    // 处理接收到的图片消息
    protected abstract String processInImageMsg(InImageMsg inImageMsg);

    // 处理接收到的语音消息
    protected abstract String processInVoiceMsg(InVoiceMsg inVoiceMsg);

    // 处理接收到的视频消息
    protected abstract String processInVideoMsg(InVideoMsg inVideoMsg);

    // 处理接收到的视频消息
    protected abstract String processInShortVideoMsg(InShortVideoMsg inShortVideoMsg);

    // 处理接收到的地址位置消息
    protected abstract String processInLocationMsg(InLocationMsg inLocationMsg);

    // 处理接收到的链接消息
    protected abstract String processInLinkMsg(InLinkMsg inLinkMsg);

    // 处理接收到的多客服管理事件
    protected abstract String processInCustomEvent(InCustomEvent inCustomEvent);

    // 处理接收到的关注/取消关注事件
    protected abstract String processInFollowEvent(InFollowEvent inFollowEvent);

    // 处理接收到的扫描带参数二维码事件
    protected abstract String processInQrCodeEvent(InQrCodeEvent inQrCodeEvent);

    // 处理接收到的上报地理位置事件
    protected abstract String processInLocationEvent(InLocationEvent inLocationEvent);

    // 处理接收到的群发任务结束时通知事件
    protected abstract String processInMassEvent(InMassEvent inMassEvent);

    // 处理接收到的自定义菜单事件
    protected abstract String processInMenuEvent(InMenuEvent inMenuEvent);

    // 处理接收到的语音识别结果
    protected abstract String processInSpeechRecognitionResults(InSpeechRecognitionResults inSpeechRecognitionResults);

    // 处理接收到的模板消息是否送达成功通知事件
    protected abstract String processInTemplateMsgEvent(InTemplateMsgEvent inTemplateMsgEvent);

    // 处理微信摇一摇事件
    protected abstract String processInShakearoundUserShakeEvent(InShakearoundUserShakeEvent inShakearoundUserShakeEvent);

    // 资质认证成功 || 名称认证成功 || 年审通知 || 认证过期失效通知
    protected abstract String processInVerifySuccessEvent(InVerifySuccessEvent inVerifySuccessEvent);

    // 资质认证失败 || 名称认证失败
    protected abstract String processInVerifyFailEvent(InVerifyFailEvent inVerifyFailEvent);

    // 门店在审核事件消息
    protected abstract String processInPoiCheckNotifyEvent(InPoiCheckNotifyEvent inPoiCheckNotifyEvent);
}
