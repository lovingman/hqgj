/**
 * project name:distribution
 * file name:CustomServiceMsgApi
 * package name:com.arvin.wechat.util
 * date:2017-03-30 14:33
 * author:Arvin
 * Copyright (c) CD Technology Co.,Ltd. All rights reserved.
 */
package com.huacainfo.ace.wxms.wechat.api;


import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.wxms.wechat.util.ApiResult;
import com.huacainfo.ace.wxms.wechat.util.HttpKit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description: //TODO 类功能描述 <br>
 * date: 2017-03-30 14:33
 *
 * @author ArvinZou
 * @version 1.0
 * @since JDK 1.8
 */
public class WeChatCustomerMsgApi {
    private static String customMessageUrl = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=";

    /**
     * 发送客服消息send
     *
     * @param accessToken 授权token
     * @param openId      微信唯一识别ID
     * @param text        消息内容
     * @return 发送结果
     */
    public static ApiResult sendText(String accessToken, String openId, String text) {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("touser", openId);
        json.put("msgtype", "text");

        Map<String, Object> textObj = new HashMap<String, Object>();
        textObj.put("content", text);

        json.put("text", textObj);
        return sendMsg(accessToken, json);
    }

    /**
     * 发送客服消息
     *
     * @param message
     * @return ApiResult
     */
    private static ApiResult sendMsg(String accessToken, Map<String, Object> message) {
        String jsonResult = HttpKit.post(customMessageUrl + accessToken, JsonUtil.toJson(message));
        return new ApiResult(jsonResult);
    }


    /**
     * 发送图文回复
     *
     * @param openId   微信唯一识别ID
     * @param articles 图文内容
     * @return ApiResult
     */
    public static ApiResult sendNews(String accessToken, String openId, List<Articles> articles) {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("touser", openId);
        json.put("msgtype", "news");

        Map<String, Object> news = new HashMap<String, Object>();
        news.put("articles", articles);

        json.put("news", news);
        return sendMsg(accessToken, json);
    }

    /**
     * 客户消息图文封装和 `News` 又略微区别，无法公用
     */
    public static class Articles {
        private String title;
        private String description;
        private String url;
        private String picurl;

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

        public String getPicurl() {
            return picurl;
        }

        public void setPicurl(String picurl) {
            this.picurl = picurl;
        }
    }
}
