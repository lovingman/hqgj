package com.huacainfo.ace.wxms.wechat.api;

import com.huacainfo.ace.common.tools.StringKit;
import com.huacainfo.ace.wxms.wechat.constant.ApiURL;
import com.huacainfo.ace.wxms.wechat.util.AmountKit;
import com.huacainfo.ace.wxms.wechat.util.HttpKit;
import com.huacainfo.ace.wxms.wechat.util.PaymentKit;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Arvin
 * @Date: 2018/11/14 09:33
 * @Description:
 */
public class WeChatPayApi {

    public static final String FAIL = "-1";
    public static final String SUCCESS = "200";

    private static Map<String, Object> rtnMsg(String rstCode, String msg, Object body) {
        Map<String, Object> rtn = new HashMap<>();
        rtn.put("return_code", "SUCCESS");
        rtn.put("rst_code", rstCode);
        rtn.put("return_msg", msg);
        rtn.put("body", body);
        return rtn;
    }


    /**
     * 微信企业付款
     * api地址：https://pay.weixin.qq.com/wiki/doc/api/tools/mch_pay.php?chapter=14_2
     *
     * @param outTradeNo 商户单号(申请表记录id)
     * @param openId     微信openid
     * @param realName   用户真实姓名
     * @param amount     支付金额（元）
     * @param desc       支付描述
     * @return
     */
    @RequestMapping("/mchPay")
    @ResponseBody
    public static Map<String, Object> mchPay(String outTradeNo, String openId, String realName, String amount, String desc,
                                             String mchAppId, String mchId, String apiKey, byte[] certBytes) {
        if (!StringKit.areNotEmpty(outTradeNo, openId, realName, amount)) {
            return rtnMsg(FAIL, "缺少付款必要信息", null);
        }
        if (!StringKit.areNotEmpty(mchAppId, mchId, apiKey)) {
            return rtnMsg(FAIL, "缺少商户配置信息", null);
        }
        if (null == certBytes || certBytes.length == 0) {
            return rtnMsg(FAIL, "缺少企业付款证书", null);
        }

        Map<String, String> params = new HashMap<>();
        params.put("mch_appid", mchAppId);                //商户账号appid
        params.put("mchid", mchId);                       //商户号
        params.put("partner_trade_no", outTradeNo);       //商户订单号
        params.put("openid", openId);                     //用户openid
        params.put("check_name", "FORCE_CHECK");          //校验用户姓名选项  NO_CHECK：不校验真实姓名 FORCE_CHECK：强校验真实姓名
        params.put("re_user_name", realName);             //收款用户姓名 收款用户真实姓名。如果check_name设置为FORCE_CHECK，则必填用户真实姓名
        params.put("amount", AmountKit.changeY2F(amount));//企业付款金额，单位为分
        params.put("desc", desc);                         //企业付款备注，必填
        params.put("device_info", "013467007045764");     //微信支付分配的终端设备号 非必选项
        params.put("spbill_create_ip", "127.0.0.1");      //Ip地址 该IP同在商户平台设置的IP白名单中的IP没有关联，该IP可传用户端或者服务端的IP。
        params.put("nonce_str", System.currentTimeMillis() + "");//随机字符串

        String sign = PaymentKit.createSign(params, apiKey);
        params.put("sign", sign);//签名
        String xmlStr;
        try {
            xmlStr = HttpKit.postSSL(ApiURL.MCH_PAY_API_URL, PaymentKit.toXml(params), certBytes, mchId);
        } catch (Exception e) {
            return rtnMsg(FAIL, "企业支付异常：\n" + e.getMessage(), null);
        }

        return rtnMsg(SUCCESS, "调用成功", PaymentKit.xmlToMap(xmlStr));
    }

    /**
     * 企业支付查询
     *
     * @param outTradeNo 商户单号(申请表记录id)
     * @return String
     */
    @RequestMapping("/mchPayQuery")
    @ResponseBody
    public static Map<String, Object> mchPayQuery(String outTradeNo,
                                                  String appId, String mchId, String apiKey, byte[] certBytes) {
        if (StringKit.isEmpty(outTradeNo)) {
            return rtnMsg(FAIL, "缺少必要信息", null);
        }
        if (!StringKit.areNotEmpty(appId, mchId, apiKey)) {
            return rtnMsg(FAIL, "缺少商户配置信息", null);
        }
        if (null == certBytes || certBytes.length == 0) {
            return rtnMsg(FAIL, "缺少企业付款证书", null);
        }
        //post参数
        Map<String, String> params = new HashMap<>();
        params.put("appid", appId);
        params.put("mch_id", mchId);
        params.put("partner_trade_no", outTradeNo);
        params.put("nonce_str", System.currentTimeMillis() + "");
        //签名算法
        String sign = PaymentKit.createSign(params, apiKey);
        params.put("sign", sign);
        String xmlStr = HttpKit.postSSL(ApiURL.MCH_PAY_QRY_API_URL, PaymentKit.toXml(params), certBytes, mchId);

        return rtnMsg(SUCCESS, "调用成功", PaymentKit.xmlToMap(xmlStr));
    }
}
