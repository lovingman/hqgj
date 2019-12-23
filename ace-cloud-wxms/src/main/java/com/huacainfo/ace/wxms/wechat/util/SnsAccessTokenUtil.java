/**
 * project name:distribution
 * file name:SnsAccessTokenUtil
 * package name:com.arvin.wechat.util
 * date:2016/8/8 15:50
 * author:haing
 * Copyright (c) CD Technology Co.,Ltd. All rights reserved.
 */
package com.huacainfo.ace.wxms.wechat.util;


import com.github.pagehelper.StringUtil;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.wxms.wechat.constant.WeChatConstants;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * description: //sns获取用户信息 <br>
 * date: 2016/8/8 15:50
 *
 * @author haing
 * @version 1.0
 * @since JDK 1.8
 */
public class SnsAccessTokenUtil {
    public SnsAccessTokenUtil() {
    }

    public static String getAuthorizeURL(String appId, String redirect_uri, boolean snsapiBase) {
        return getAuthorizeURL(appId, redirect_uri, (String) null, snsapiBase);
    }

    public static String getAuthorizeURL(String appId, String redirect_uri, String state, boolean snsapiBase) {
        HashMap params = new HashMap();
        params.put("appid", appId);
        params.put("response_type", "code");
        params.put("redirect_uri", redirect_uri);
        if (snsapiBase) {
            params.put("scope", "snsapi_base");
        } else {
            params.put("scope", "snsapi_userinfo");
        }

        if (StringUtil.isEmpty(state)) {
            params.put("state", "wx#wechat_redirect");
        } else {
            params.put("state", state.concat("#wechat_redirect"));
        }

        String para = PaymentKit.packageSign(params);
        return WeChatConstants.AUTHORIZE_URI + "?" + para;
    }

    public static String getQrConnectURL(String appId, String redirect_uri) {
        return getQrConnectURL(appId, redirect_uri, (String) null);
    }

    public static String getQrConnectURL(String appId, String redirect_uri, String state) {
        HashMap params = new HashMap();
        params.put("appid", appId);
        params.put("response_type", "code");
        params.put("redirect_uri", redirect_uri);
        params.put("scope", "snsapi_login");
        if (StringUtil.isEmpty(state)) {
            params.put("state", "wx#wechat_redirect");
        } else {
            params.put("state", state.concat("#wechat_redirect"));
        }

        String para = PaymentKit.packageSign(params);
        return WeChatConstants.QRCONNECT_RUL + "?" + para;
    }

    public static SnsAccessToken getSnsAccessToken(String appId, String secret, String code) {
        final Map queryParas =
                ParaMap.create("appid", appId)
                        .put("secret", secret)
                        .put("code", code).getData();
        return (SnsAccessToken) RetryUtils.retryOnException(3, new Callable() {
            @Override
            public SnsAccessToken call() throws Exception {
                String json = HttpKit.get(WeChatConstants.AUTH_CODE_URL, queryParas);
                return new SnsAccessToken(json);
            }
        });
    }
}
