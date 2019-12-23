package com.huacainfo.ace.wxms.wechat.constant;

/**
 * Created by liuhaiyin on 2016/6/29.
 */
public interface WeChatConstants {

    String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=";
    String SEND_MODOU_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
    String TAKE_USER_INFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
    // String NORMAL_CODE = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
    // final String OPENID_GET = "https://www.hubeta.com/wechat-api/wechat/info/getOpenId?params=";

    String AUTH_CODE_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?grant_type=authorization_code";
    String AUTHORIZE_URI = "https://open.weixin.qq.com/connect/oauth2/authorize";
    String QRCONNECT_RUL = "https://open.weixin.qq.com/connect/qrconnect";


    String AUTH_CODE_URL_2 = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
}
