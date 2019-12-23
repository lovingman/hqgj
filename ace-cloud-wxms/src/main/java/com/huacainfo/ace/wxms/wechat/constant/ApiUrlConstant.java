package com.huacainfo.ace.wxms.wechat.constant;

/**
 * @author ArvinZou
 */
public interface ApiUrlConstant {

    /**
     * 模板消息api调用地址
     */
    String MESSAGE_TEMPLATE = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";

    /**
     * ACCESS_TOKEN 令牌获取地址
     */
    String ACCESS_TOKEN_API_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=#APPID#&secret=#APPSECRET#";

    /**
     * 菜单创建接口
     */
    String MENU_CREATE_API_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=#ACCESS_TOKEN#";

    /**
     * 微信支付接口地址
     */
    String WX_PAY_PATH_API_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    /**
     * jsapi_ticket
     */
    String WX_JSAPI_TICKET_API_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=#ACCESS_TOKEN#&type=jsapi";

    /**
     * 获取用户列表
     */
    String USER_LIST_API_URL = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=#ACCESS_TOKEN#&next_openid=#NEXT_OPENID#";

    /**
     * 商户号 - 企业付款接口地址
     */
    String MCH_PAY_API_URL = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";
    /**
     * 商户号 - 企业付款结果查询接口地址
     */
    String MCH_PAY_QRY_API_URL = "https://api.mch.weixin.qq.com/mmpaymkttransfers/gettransferinfo";

    /**
     * 微信网页授权
     * 第一步：用户同意授权，获取code
     */
    String JSAPI_AUTH_AUTHORIZE = "https://open.weixin.qq.com/connect/oauth2/authorize";

    /**
     * 微信网页授权
     * 第二步：通过code换取网页授权access_token
     */
    String JSAPI_AUTH_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=#APPID#&secret=#SECRET#&code=#CODE#&grant_type=authorization_code";
    /**
     * 微信网页授权
     * 第三步：刷新access_token（如果需要）
     */
    String JSAPI_AUTH_REFRESH_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=#APPID#&grant_type=refresh_token&refresh_token=#REFRESH_TOKEN#";
    /**
     * 微信网页授权
     * 第四步：拉取用户信息(需scope为 snsapi_userinfo)
     */
    String JSAPI_AUTH_USERINFO = "https://api.weixin.qq.com/sns/userinfo?access_token=#ACCESS_TOKEN#&openid=#OPENID#&lang=#LANG#";

}
