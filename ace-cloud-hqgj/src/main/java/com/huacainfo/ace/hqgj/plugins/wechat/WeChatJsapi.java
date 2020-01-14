package com.huacainfo.ace.hqgj.plugins.wechat;

import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.common.tools.StringKit;
import com.huacainfo.ace.hqgj.plugins.wechat.constant.ApiUrlConstant;
import com.huacainfo.ace.hqgj.plugins.wechat.kit.HttpKit;
import com.huacainfo.ace.hqgj.plugins.wechat.kit.MD5Kit;
import com.huacainfo.ace.hqgj.plugins.wechat.kit.PaymentKit;
import com.huacainfo.ace.hqgj.plugins.wechat.pojo.Userinfo;
import com.huacainfo.ace.hqgj.plugins.wechat.pojo.resp.JsapiAccessTokenResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @ClassName WeChatJsapi
 * @Description 微信网页授权Api
 * @Author Arvin Zou
 * @Date 2019/9/17 16:30
 */
public class WeChatJsapi {
    protected static Logger logger = LoggerFactory.getLogger(WeChatJsapi.class);

    /**
     * 第一步：用户同意授权，获取code
     * <p>
     * 在确保微信公众账号拥有授权作用域（scope参数）的权限的前提下（服务号获得高级接口后，
     * 默认拥有scope参数中的snsapi_base和snsapi_userinfo），引导关注者打开如下页面：
     * https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect
     * 若提示“该链接无法访问”，请检查参数是否填写错误，是否拥有scope参数对应的授权作用域权限。
     * <p>
     * 尤其注意：由于授权操作安全等级较高，所以在发起授权请求时，微信会对授权链接做正则强匹配校验，如果链接的参数顺序不对，授权页面将无法正常访问
     *
     * @param appId       (必填)公众号的唯一标识
     * @param redirectUri (必填)授权后重定向的回调链接地址， 请使用 urlEncode 对链接进行处理
     * @param state       (必填)重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值，最多128字节
     * @param scope       (必填)true - 不弹出授权页面，直接跳转，只能获取用户openid;
     *                    false - 弹出授权页面，可通过openid拿到昵称、性别、所在地。并且， 即使在未关注的情况下，只要用户授权，也能获取其信息
     * @return String redirectUri
     */
    public static String getAuthUri(String appId, String redirectUri, String state, boolean scope) {
        HashMap params = new HashMap();
        params.put("appid", appId);
        params.put("response_type", "code");
        try {
            params.put("redirect_uri", URLEncoder.encode(redirectUri, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            logger.error("URLEncoder 异常: {}", e);
        }
        if (scope) {
            params.put("scope", "snsapi_base");
        } else {
            params.put("scope", "snsapi_userinfo");
        }

        if (StringKit.isEmpty(state)) {
            params.put("state", "jsapi_auth#wechat_redirect");
        } else {
            params.put("state", state.concat("#wechat_redirect"));
        }

        String para = PaymentKit.packageSign(params);
        return ApiUrlConstant.JSAPI_AUTH_AUTHORIZE + "?" + para;
    }

    /**
     * 方法重载
     * 第一步：用户同意授权，获取code;
     * 默认state值: state=jsapi_auth
     *
     * @param appId        (必填)公众号的唯一标识
     * @param redirect_uri (必填)授权后重定向的回调链接地址， 请使用 urlEncode 对链接进行处理
     * @param scope        (必填)true - 不弹出授权页面，直接跳转，只能获取用户openid;
     *                     false - 弹出授权页面，可通过openid拿到昵称、性别、所在地。并且， 即使在未关注的情况下，只要用户授权，也能获取其信息
     * @return String redirectUri
     */
    public static String getAuthUri(String appId, String redirect_uri, boolean scope) {
        return getAuthUri(appId, redirect_uri, (String) null, scope);
    }

    /**
     * 第二步：通过code换取网页授权access_token
     * <p>
     * 首先请注意，这里通过code换取的是一个特殊的网页授权access_token,
     * 与基础支持中的access_token（该access_token用于调用其他接口）不同。
     * 公众号可通过下述接口来获取网页授权access_token。如果网页授权的作用域为snsapi_base，
     * 则本步骤中获取到网页授权access_token的同时，也获取到了openid，snsapi_base式的网页授权流程即到此为止。
     * <p>
     * 尤其注意：由于公众号的secret和获取到的access_token安全级别都非常高，必须只保存在服务器，不允许传给客户端。
     * 后续刷新access_token、通过access_token获取用户信息等步骤，也必须从服务器发起。
     *
     * @param appId  (必填)公众号的唯一标识
     * @param secret (必填)公众号的appsecret
     * @param code   (必填)填写第一步获取的code参数
     * @return JsapiAccessTokenResp
     */
    public static JsapiAccessTokenResp getJsapiAccessToken(String appId, String secret, String code) {

        String api = ApiUrlConstant.JSAPI_AUTH_ACCESS_TOKEN
                .replace("#APPID#", appId)
                .replace("#SECRET#", secret)
                .replace("#CODE#", code);

        return JsonUtil.toObject(HttpKit.get(api), JsapiAccessTokenResp.class);
    }

    /**
     * 第三步：刷新access_token（如果需要）
     *
     * 由于access_token拥有较短的有效期，当access_token超时后，可以使用refresh_token进行刷新;
     * refresh_token有效期为30天，当refresh_token失效之后，需要用户重新授权。
     */

    /**
     * 第四步：拉取用户信息(需scope为 snsapi_userinfo)
     * <p>
     * 如果网页授权作用域为snsapi_userinfo，则此时开发者可以通过access_token和openid拉取用户信息了。
     * <p>
     * 请求方法
     * http：GET（请使用https协议） https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
     *
     * @param accessToken (必填)网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
     * @param openid      (必填)用户的唯一标识;公众号下,用户唯一值
     * @param lang        (可选)返回国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语
     */
    public static Userinfo getUserinfo(String accessToken, String openid, String lang) {
        //设定默认值
        lang = StringKit.isEmpty(lang) ? "zh_CN" : lang;
        //调用地址
        String api = ApiUrlConstant.JSAPI_AUTH_USERINFO
                .replace("#ACCESS_TOKEN#", accessToken)
                .replace("#OPENID#", openid)
                .replace("#LANG#", lang);

        return JsonUtil.toObject(HttpKit.get(api), Userinfo.class);
    }

    /**
     * 方法重载
     * 第四步：拉取用户信息(需scope为 snsapi_userinfo)
     * <p>
     * 如果网页授权作用域为 snsapi_userinfo，则此时开发者可以通过access_token和openid拉取用户信息了。
     * <p>
     * 请求方法
     * http：GET（请使用https协议） https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
     *
     * @param accessToken (必填)网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
     * @param openid      (必填)用户的唯一标识;公众号下,用户唯一值
     */
    public static Userinfo getUserinfo(String accessToken, String openid) {
        return getUserinfo(accessToken, openid, "zh_CN");
    }


    /**
     * JSAPI 签名
     * 注意：
     * 1.签名用的noncestr和timestamp必须与wx.config中的nonceStr和timestamp相同。
     * 签名用的url必须是调用JS接口页面的完整URL。
     *
     * @param nonceStr  随机字符串
     * @param ticket    票据
     * @param timestamp 时间戳
     * @param url       页面地址
     * @return String
     */
    public static String getJsapiSign(String nonceStr, String ticket, long timestamp, String url) {
        SortedMap<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("jsapi_ticket", ticket);
        parameters.put("noncestr", nonceStr);
        parameters.put("timestamp", timestamp);
        parameters.put("url", url);

        String characterEncoding = "UTF-8";
        String Sign = createSign("SHA1", characterEncoding, parameters, null);
        return Sign;
    }

    /**
     * 创建Sign
     *
     * @param characterEncoding 字符编码
     * @param parameters        参数
     * @return String
     */
    public static String createSign(String signType, String characterEncoding, SortedMap<String, Object> parameters, String apiKey) {
        String sign = "";
        if (!CollectionUtils.isEmpty(parameters)) {
            StringBuffer sb = new StringBuffer();
            for (Map.Entry<String, Object> entry : parameters.entrySet()) {
                String k = entry.getKey();
                Object v = entry.getValue();
                if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                    sb.append(k).append('=').append(v).append('&');
                }
            }

            if (signType.equals("MD5")) {
                sb.append("key=").append(apiKey);
                sign = MD5Kit.MD5Encode(sb.toString(), characterEncoding).toUpperCase();
            } else if (signType.equals("SHA1")) {
                //处理最后一个&符号
                //http://mp.weixin.qq.com/debug/cgi-bin/sandbox?t=jsapisign 测试链接
                String tempStr = sb.toString().substring(0, sb.length() - 1);
                logger.debug("tempStr={}", tempStr);
                //不用进行upper
                sign = MD5Kit.sha1(tempStr);
            }
        }
        return sign;
    }
}

