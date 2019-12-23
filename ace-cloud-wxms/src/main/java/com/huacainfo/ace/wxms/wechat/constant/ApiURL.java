package com.huacainfo.ace.wxms.wechat.constant;

/**
 * Created by HuaCai008 on 2018/4/16.
 */
public interface ApiURL {


    String https = "https://";
//    String http = "http://";

    /**
     * 模板消息api调用地址
     */
    String MESSAGE_TEMPLATE = https + "api.weixin.qq.com/cgi-bin/message/template/send?access_token=";

    /**
     * ACCESS_TOKEN 令牌获取地址
     */
    String ACCESS_TOKEN_API_URL = https + "api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=#APPID#&secret=#APPSECRET#";

    /**
     * 菜单创建接口
     */
    String MENU_CREATE_API_URL = https + "api.weixin.qq.com/cgi-bin/menu/create?access_token=#ACCESS_TOKEN#";

    /**
     * 微信支付接口地址
     */
    String WX_PAY_PATH_API_URL = https + "api.mch.weixin.qq.com/pay/unifiedorder";

    /**
     * jsapi_ticket
     */
    String WX_JSAPI_TICKET_API_URL = https + "api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=#ACCESS_TOKEN#&type=jsapi";

    /**
     * 获取用户列表
     */
    String USER_LIST_API_URL = https + "api.weixin.qq.com/cgi-bin/user/get?access_token=#ACCESS_TOKEN#&next_openid=#NEXT_OPENID#";

    /**
     * 商户号 - 企业付款接口地址
     */
    String MCH_PAY_API_URL = https + "api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";
    /**
     * 商户号 - 企业付款结果查询接口地址
     */
    String MCH_PAY_QRY_API_URL = https + "api.mch.weixin.qq.com/mmpaymkttransfers/gettransferinfo";


}
