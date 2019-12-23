package com.huacainfo.ace.wxms.wechat.api;


import com.huacainfo.ace.wxms.wechat.constant.ApiURL;
import com.huacainfo.ace.wxms.wechat.entity.TemplateData;
import com.huacainfo.ace.wxms.wechat.util.HttpKit;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by HuaCai008 on 2018/4/16.
 */
public class MessageSendApi {

    public static final String TOP_COLOR = "#FF0000";
    public static final String TEXT_COLOR = "#173177";

    public static String sendTemplate(String accessToken, TemplateData templateData) {

        return HttpKit.post(ApiURL.MESSAGE_TEMPLATE + accessToken, templateData.toString());
    }

    /**
     * @param toOpenId
     * @param templateId
     * @param url
     * @param params
     * @return
     */
    public static TemplateData buildTemplateData(String toOpenId, String templateId, String url, Map<String, String> params) {
        TemplateData temp = new TemplateData();
        temp.setTopcolor(TOP_COLOR);
        temp.setTouser(toOpenId);
        temp.setTemplate_id(templateId);
        //详情链接
        temp.setUrl(null == url ? "" : url);

        for (Map.Entry<String, String> entry : params.entrySet()) {
            temp.add(entry.getKey(), entry.getValue(), TEXT_COLOR);
        }

        return temp;
    }


    public static void main(String[] args) {

        String openId = "oFvIjw9bgtJmgvqVv0XIayPsh2QI";
        String templateId = "kdRZ39sBzGCXaXSz20-s3x8WAV1cyWxeKoaVaspl3qE";
        String url = "www.qq.com";

        Map<String, String> params = new HashMap<>();
        params.put("first", "违章记录通知");
        params.put("keyword1", "泞泞泞?");
        params.put("keyword2", "湘J·XXXXX");
        params.put("keyword3", "2018年04月10日");
        params.put("keyword4", "湖南省常德市XXX");
        params.put("remark", "您有的车辆新违章，请及时查收!");
        TemplateData data = buildTemplateData(openId, templateId, url, params);

        String token = "8_PIrfIfVNpviwF9WFOdk2zb4Gh83Q7NtiO6fz_rbw7dfnZhh8Rh2_1F5qWP8w199EQc3XyOYw9bme6sRD5-R_VhSRU8uvVBqHqmDjUMV87EenzF0Dd5SC4odQ7jATnWgID6D4KQEYjp84EW06VWSaAIARSO";
        System.out.println(sendTemplate(token, data));
    }
}
