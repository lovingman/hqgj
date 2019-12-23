package com.huacainfo.ace.wxms.wechat.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.io.ByteArrayInputStream;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * PackageName:com.arvin.common.plugin.wxpay<br/>
 * Descript:微信支付工具类 <br/>
 * Date: 2016-04-18 <br/>
 * User: Bovine
 * version 1.0
 */
public class WeChatPayUtil {


    protected static Logger logger = LoggerFactory.getLogger(WeChatPayUtil.class);

    public static String initXML(String appid, String deviceInfo, String mchId,
                                 String openid, String total_fee, String sign, String body,
                                 String attch, String nonceStr, String outTradeNo, String notifyUrl) {
        String moneyFen = AmountKit.changeY2F(total_fee);
        String XML = "<xml>" +
                "<appid>" + appid + "</appid>" +
                "<attach>" + attch + "</attach>" +
                "<body>" + body + "</body>" +
                "<device_info>" + deviceInfo + "</device_info>" +
                "<mch_id>" + mchId + "</mch_id>" +
                "<nonce_str>" + nonceStr + "</nonce_str>" +
                "<notify_url>" + notifyUrl + "</notify_url>" +
                "<openid>" + openid + "</openid>" +
                "<out_trade_no>" + outTradeNo + "</out_trade_no>" +
                "<spbill_create_ip>127.0.0.1</spbill_create_ip>" +
                "<total_fee>" + moneyFen + "</total_fee>" +
                "<trade_type>JSAPI</trade_type>" +
                "<sign>" + sign + "</sign>" +
                "</xml>";
        return XML;
    }

    public static String getSign(String appid, String deviceInfo, String mchId, String apiKey,
                                 String openid, String total_fee, String body, String attch,
                                 String nonceStr, String outTradeNo, String notifyUrl) {
        String moneyFen = AmountKit.changeY2F(total_fee);
        SortedMap<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("appid", appid);
        parameters.put("attach", attch);
        parameters.put("body", body);
        parameters.put("device_info", deviceInfo);
        parameters.put("mch_id", mchId);
        parameters.put("nonce_str", nonceStr);
        parameters.put("notify_url", notifyUrl);

        parameters.put("openid", openid);
        parameters.put("out_trade_no", outTradeNo);
        parameters.put("spbill_create_ip", "127.0.0.1");//手机访问IP地址
        parameters.put("total_fee", moneyFen);
        parameters.put("trade_type", "JSAPI");

        return createSign("MD5", "UTF-8", parameters, apiKey);
    }

    /**
     * JSAPI 签名
     * 注意：
     * 1.签名用的noncestr和timestamp必须与wx.config中的nonceStr和timestamp相同。
     * 签名用的url必须是调用JS接口页面的完整URL。
     *
     * @param nonceStr
     * @param ticket
     * @param timestamp
     * @param url
     * @return
     */
    public static String getJsApiSign(String nonceStr, String ticket, String timestamp, String url) {
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
     * ΢创建Sign
     *
     * @param characterEncoding
     * @param parameters
     * @return
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
                sign = MD5.MD5Encode(sb.toString(), characterEncoding).toUpperCase();
            } else if (signType.equals("SHA1")) {
                //处理最后一个&符号
                //http://mp.weixin.qq.com/debug/cgi-bin/sandbox?t=jsapisign 测试链接
                String tempStr = sb.toString().substring(0, sb.length() - 1);
                logger.debug("tempStr={}", tempStr);
                sign = Sha1Util.getSha1(tempStr);//不用进行upper
            }
        }
        return sign;
    }

    public static void main(String[] args) {
        String str = "kgt8ON7yVITDhtdwci0qefheBoA_OYrLAOR5TzL64upLdPzDP0lTYPbOaqHgnIjUYTytClrGCTxB0jV3MZlWJA&noncestr=5wf9fhes1dc0oxr6taavgfjzos2vbbnl&timestamp=1476784251&url=http://www.hubeta.com/wechat-api/resources/TYZY/html/order/present.html";
        String sign = Sha1Util.getSha1(str);
        System.out.println(sign);
    }

    public static String getPayNo(String url, String xmlParam) {
        logger.debug("xml是:" + xmlParam);
        String prepay_id = "";
        try {
            String resultJson = HttpKit.post(url, xmlParam);
            logger.debug("resultJson={}", resultJson);
            if (resultJson.indexOf("FAIL") != -1) {
                return prepay_id;
            }
            Map map = MessageUtil.xmlToMap(new ByteArrayInputStream(resultJson.getBytes()));
            prepay_id = String.valueOf(map.get("prepay_id"));
        } catch (Exception e) {
            logger.error("run error：{}", e);
        }
        return prepay_id;
    }
}
