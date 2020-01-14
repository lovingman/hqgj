package com.huacainfo.ace.hqgj.plugins.wechat;


import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.hqgj.plugins.wechat.constant.ApiUrlConstant;
import com.huacainfo.ace.hqgj.plugins.wechat.kit.HttpKit;
import com.huacainfo.ace.hqgj.plugins.wechat.pojo.TemplateData;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ArvinZou
 * description: 微信模板消息发送接口工具类
 */
public class WeChatTemplateMsgApi {

    public static final String TOP_COLOR = "#FF0000";
    public static final String TEXT_COLOR = "#173177";

    /**
     * 消息发送接口
     *
     * @param accessToken 接口统一令牌
     * @param data        发送数据
     * @return 接口调用返回结果
     * {
     * "errcode":0,
     * "errmsg":"ok",
     * "msgid":200228332
     * }
     */
    public static Map<String, Object> send(String accessToken, TemplateData data) {
        return JsonUtil.toMap(HttpKit.post(ApiUrlConstant.MESSAGE_TEMPLATE.concat(accessToken), data.toString()));
    }

    /**
     * 创建模板消息发送数据
     *
     * @param openid     送达人openid
     * @param templateId 模板消息ID
     * @param linkUrl    详情链接
     * @param params     其他参数
     * @return TemplateData
     */
    public static TemplateData create(String openid, String templateId, String linkUrl, Map<String, String> params) {
        TemplateData temp = new TemplateData();
        temp.setTopcolor(TOP_COLOR);
        temp.setTouser(openid);
        temp.setTemplate_id(templateId);
        //详情链接
        temp.setUrl(null == linkUrl ? "" : linkUrl);

        for (Map.Entry<String, String> entry : params.entrySet()) {
            temp.add(entry.getKey(), entry.getValue(), TEXT_COLOR);
        }

        return temp;
    }

    /**
     * 调用示例
     */
    public static void main(String[] args) {

        String openId = "oFvIjw9bgtJmgvqVv0XIayPsh2QI";
        String templateId = "kdRZ39sBzGCXaXSz20-s3x8WAV1cyWxeKoaVaspl3qE";
        String linkUrl = "www.qq.com";

        Map<String, String> params = new HashMap<>();
        params.put("first", "违章记录通知");
        params.put("keyword1", "泞泞泞?");
        params.put("keyword2", "湘J·XXXXX");
        params.put("keyword3", "2018年04月10日");
        params.put("keyword4", "湖南省常德市XXX");
        params.put("remark", "您有的车辆新违章，请及时查收!");
        TemplateData data = WeChatTemplateMsgApi.create(openId, templateId, linkUrl, params);

        String token = "8_PIrfIfVNpviwF9WFOdk2zb4Gh83Q7NtiO6fz_rbw7dfnZhh8Rh2_1F5qWP8w199EQc3XyOYw9bme6sRD5-R_VhSRU8uvVBqHqmDjUMV87EenzF0Dd5SC4odQ7jATnWgID6D4KQEYjp84EW06VWSaAIARSO";
        System.out.println(send(token, data));
    }
}
