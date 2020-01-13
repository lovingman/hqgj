package com.huacainfo.ace.hqgj.plugins.wechat;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.hqgj.model.WechatConfig;
import com.huacainfo.ace.hqgj.plugins.wechat.constant.ApiUrlConstant;
import com.huacainfo.ace.hqgj.plugins.wechat.constant.WeChatReturnCode;
import com.huacainfo.ace.hqgj.plugins.wechat.kit.HttpKit;
import com.huacainfo.ace.hqgj.plugins.wechat.pojo.Menu;
import com.huacainfo.ace.hqgj.plugins.wechat.pojo.UserList;
import com.huacainfo.ace.hqgj.plugins.wechat.pojo.resp.AccessTokenResp;
import com.huacainfo.ace.hqgj.plugins.wechat.pojo.resp.TicketResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ArvinZou
 */
public class WeChatApi {

    /**
     * 原始ID
     */
    public static final String ORIGINAL_ID = "";
    /**
     * appID
     */
    public static final String APP_ID = "";
    /**
     * app密钥
     */
    public static final String APP_SECRET = "";
    /**
     * 公众号服务平台token字符串
     */
    public static final String WX_TOKEN = "";
    /**
     * 商户企业ID
     */
    public static final String MC_ID = "";
    /**
     * 商户密钥
     */
    public static final String API_KEY = "";
    public static final String DEVICE_INFO = "WEB";
    protected static Logger logger = LoggerFactory.getLogger(WeChatApi.class);

    /***
     * 获取access_token ;
     * 公众号和小程序通用
     *
     * @param appId APPID
     * @param appSecret APPSECRET
     * @return AccessToken
     */
    public static ResponseDTO<WechatConfig> getAccessToken(String appId, String appSecret) {
        // 1、获取accessToken
        String url = ApiUrlConstant.ACCESS_TOKEN_API_URL
                .replace("#APPID#", appId)
                .replace("#APPSECRET#", appSecret);

        String respStr = HttpKit.get(url);
        logger.debug("WeChatApi.getAccessToken.appId[{}].response[ {} ]", appId, respStr);
        //token返回
        AccessTokenResp token = JsonUtil.toObject(respStr, AccessTokenResp.class);
        //获取accessToken失败,结果返回
        if (token.getErrcode() != 0) {
            return new ResponseDTO<>(ResultCode.FAIL, "获取accessToken失败: " + respStr);
        }

        // 2、获取授权页ticket
        String jsapi = ApiUrlConstant.WX_JSAPI_TICKET_API_URL
                .replace("#ACCESS_TOKEN#", token.getAccess_token());

        String ticketResp = HttpKit.get(jsapi);
        logger.debug("WeChatApi.getAccessToken.ACCESS_TOKEN[{}].response[ {} ]", token.getAccess_token(), ticketResp);
        TicketResp ticket = JsonUtil.toObject(ticketResp, TicketResp.class);
        //获取授权页ticket失败,结果返回
        if (ticket.getErrcode() != WeChatReturnCode.OK_0) {
            return new ResponseDTO<>(ResultCode.FAIL, "获取授权页ticket失败:" + ticket);
        }

        // 3、方法结果返回
        WechatConfig rst = new WechatConfig();
        rst.setAppId(appId);
        rst.setAppSecret(appSecret);
        rst.setAccessToken(token.getAccess_token());
        rst.setTicket(ticket.getTicket());
        rst.setExpiresIn(token.getExpires_in());
        rst.setRefreshTime(DateUtil.getNowDate());
        rst.setDeadTime(DateUtil.getNowAfterSecTime(7100));

        return new ResponseDTO<>(ResultCode.SUCCESS, "SUCCESS", rst);
    }


    /**
     * 创建菜单
     *
     * @param accessToken
     * @param menu
     * @return
     */
    public static String createMenu(String accessToken, Menu menu) {
        String url = ApiUrlConstant.MENU_CREATE_API_URL.replace("#ACCESS_TOKEN#", accessToken);
        return HttpKit.post(url, JsonUtil.toJson(menu));

    }

    /**
     * 获取公众号用户列表
     *
     * @param accessToken 调用接口凭证
     * @param nextOpenid  第一个拉取的OPENID，不填默认从头开始拉取
     * @return
     */
    public static UserList getUserList(String accessToken, String nextOpenid) {
        String url = ApiUrlConstant.USER_LIST_API_URL
                .replace("#ACCESS_TOKEN#", accessToken)
                .replace("#NEXT_OPENID#", nextOpenid);
        String r = HttpKit.get(url);

        return JsonUtil.toObject(r, UserList.class);
    }
}
