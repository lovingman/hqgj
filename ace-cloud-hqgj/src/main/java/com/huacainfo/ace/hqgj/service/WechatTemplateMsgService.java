package com.huacainfo.ace.hqgj.service;

import com.huacainfo.ace.common.dto.ResponseDTO;

/**
 * @ClassName WechatTemplateMsgService
 * @Description TODO
 * @Author Arvin Zou
 * @Date 2019/9/18 10:23
 */
public interface WechatTemplateMsgService {

    /**
     * 志愿服务报名成功通知
     *
     * @param openid  (必填)送达人openid
     * @param first   (可选,建议填写)通知标题语句块
     * @param remark  (可选)备注语句块
     * @param linkUri (可选)点击消息跳转地址
     * @param topic   (必填)活动主题语句块
     * @param timeStr (必填)活动时间语句块
     * @param contact (必填)联系人语句块
     * @param address (必填)活动地点语句块
     * @param detail  (必填)活动详情语句块
     * @return ResponseDTO
     */
    ResponseDTO notice(String openid,
                       String first, String remark, String linkUri,
                       String topic, String timeStr, String contact, String address, String detail);
}
