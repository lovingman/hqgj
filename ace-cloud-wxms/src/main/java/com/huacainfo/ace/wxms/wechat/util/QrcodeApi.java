/**
 * project name:distribution
 * file name:QrcodeApi
 * package name:com.arvin.wechat.util
 * date:2016/7/18 20:48
 * author:haing
 * Copyright (c) CD Technology Co.,Ltd. All rights reserved.
 */
package com.huacainfo.ace.wxms.wechat.util;

import com.huacainfo.ace.common.tools.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * description: // 生成带参数的二维码 API <br>
 * date: 2016/7/18 20:48
 *
 * @author haing
 * @version 1.0
 * @since JDK 1.8
 */
public class QrcodeApi {
    public static final int EXPIRE_SECONDS_30_DAY = 2592000;
    public static final int EXPIRE_SECONDS_7_DAY = 604800;
    private static Logger logger = LoggerFactory.getLogger(QrcodeApi.class);
    private static String apiUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=";
    private static String showQrcodeUrl = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=";

    public static ApiResult create(String jsonStr, String accessToken) {
        String jsonResult = HttpKit.post(apiUrl + accessToken, jsonStr);
        logger.debug("QrcodeApi.create.jsonResult={}", jsonResult);
        return new ApiResult(jsonResult);
    }

    /**
     * 创建临时二维码
     *
     * @param expireSeconds 该二维码有效时间，以秒为单位。  最大不超过2592000（即30天）。
     * @param sceneId       场景值ID，临时二维码时为32位非0整型
     * @return ApiResult 二维码信息
     */
    public static ApiResult createTemporary(int expireSeconds, String sceneId, String accessToken) {
        Map<String, Object> params = new HashMap<>();
        params.put("expire_seconds", expireSeconds);
        params.put("action_name", "QR_STR_SCENE");

        Map<String, Object> actionInfo = new HashMap<>();
        Map<String, Object> scene = new HashMap<>();
        scene.put("scene_str", sceneId);

        actionInfo.put("scene", scene);
        params.put("action_info", actionInfo);
        return create(JsonUtil.toJson(params), accessToken);
    }

    /**
     * 创建临时二维码
     *
     * @param expireSeconds 该二维码有效时间，以秒为单位。  最大不超过2592000（即30天）
     * @param sceneId       场景值ID，临时二维码时为32位非0整型
     * @return ApiResult 二维码信息
     */
    public static ApiResult createTemporary(int expireSeconds, int sceneId, String accessToken) {
        Map<String, Object> params = new HashMap<>();
        params.put("expire_seconds", expireSeconds);
        params.put("action_name", "QR_SCENE");

        Map<String, Object> actionInfo = new HashMap<>();
        Map<String, Object> scene = new HashMap<>();
        scene.put("scene_id", sceneId);

        actionInfo.put("scene", scene);
        params.put("action_info", actionInfo);
        return create(JsonUtil.toJson(params), accessToken);
    }

    /**
     * 创建永久二维码
     *
     * @param sceneId 场景值ID，永久二维码时最大值为100000（目前参数只支持1--100000）
     * @return ApiResult 二维码信息
     */
    public static ApiResult createPermanent(int sceneId, String accessToken) {
        Map<String, Object> params = new HashMap<>();
        params.put("action_name", "QR_LIMIT_SCENE");

        Map<String, Object> actionInfo = new HashMap<>();
        Map<String, Object> scene = new HashMap<>();
        scene.put("scene_id", sceneId);

        actionInfo.put("scene", scene);
        params.put("action_info", actionInfo);
        return create(JsonUtil.toJson(params), accessToken);
    }

    /**
     * 创建永久二维码
     *
     * @param sceneStr 场景值ID（字符串形式的ID），字符串类型，长度限制为1到64，仅永久二维码支持此字段
     * @return ApiResult 二维码信息
     */
    public static ApiResult createPermanent(String sceneStr, String accessToken) {
        Map<String, Object> params = new HashMap<>();
        params.put("action_name", "QR_LIMIT_STR_SCENE");

        Map<String, Object> actionInfo = new HashMap<>();
        Map<String, Object> scene = new HashMap<>();
        scene.put("scene_str", sceneStr);

        actionInfo.put("scene", scene);
        params.put("action_info", actionInfo);
        return create(JsonUtil.toJson(params), accessToken);
    }

    /**
     * 通过ticket换取二维码地址
     *
     * @param ticket 换取二维码参数
     * @return String url
     */
    public static String getShowQrcodeUrl(String ticket) {
        return showQrcodeUrl + ticket;
    }
}
