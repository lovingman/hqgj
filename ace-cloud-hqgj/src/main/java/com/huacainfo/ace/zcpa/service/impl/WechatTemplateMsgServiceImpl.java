package com.huacainfo.ace.zcpa.service.impl;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.tools.StringKit;
import com.huacainfo.ace.zcpa.plugins.wechat.WeChatTemplateMsgApi;
import com.huacainfo.ace.zcpa.plugins.wechat.constant.WeChatReturnCode;
import com.huacainfo.ace.zcpa.plugins.wechat.kit.HttpKit;
import com.huacainfo.ace.zcpa.plugins.wechat.pojo.TemplateData;
import com.huacainfo.ace.zcpa.service.WechatConfigService;
import com.huacainfo.ace.zcpa.service.WechatTemplateMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName WechatTemplateMsgServiceImpl
 * @Description 模板消息发送接口实现类
 * @Author Arvin Zou
 * @Date 2019/9/18 10:24
 */
@Service
public class WechatTemplateMsgServiceImpl implements WechatTemplateMsgService {

    /**
     * {{first.DATA}}
     * 活动主题：{{keyword1.DATA}}
     * 活动时间：{{keyword2.DATA}}
     * 联系人：{{keyword3.DATA}}
     * 活动地点：{{keyword4.DATA}}
     * 活动详情：{{keyword5.DATA}}
     * {{remark.DATA}}
     * <p>
     * 李四志愿者，您已成功报名志愿服务活动，详情如下：
     * 活动主题：【孔雀湖社区】护湖志愿服务活动
     * 活动时间：2019年2月27日 12:00至2019年2月27日 18:00
     * 联系人：张三
     * 活动地点：孔雀湖社区
     * 活动详情：孔雀湖社区开展护湖志愿服务活动，请党员报名参加护湖行动的志愿者自带清扫工具，于指定时间到指定地点集合，活动期间服从组织者安排。
     * 请按时参加，谢谢！
     */
    public static final String TEMPLATE_VOLUNTEER_SERVICE_ENROLL_SUCCESS = "yeIU6HEKZHaVSEOG-qpt2PYVZFN1nXTSAyrmYri5pAU";
    @Autowired
    private WechatConfigService configService;

    @Override
    public ResponseDTO notice(String openid,
                              String first, String remark, String linkUri,
                              String topic, String timeStr, String contact, String address, String detail) {
        //构建发送参数
        Map<String, String> params = new HashMap<>();
        if (StringKit.isNotEmpty(first)) {
            params.put("first", first);
        }
        if (StringKit.isNotEmpty(remark)) {
            params.put("remark", remark);
        }
        params.put("keyword1", topic);
        params.put("keyword2", timeStr);
        params.put("keyword3", contact);
        params.put("keyword4", address);
        params.put("keyword5", detail);
        TemplateData data = WeChatTemplateMsgApi.create(openid, TEMPLATE_VOLUNTEER_SERVICE_ENROLL_SUCCESS, linkUri, params);
        //获取 AccessToken
        String accessToken = getAccessToken();
        //发送接口调用
        Map<String, Object> rst = WeChatTemplateMsgApi.send(accessToken, data);
        if (Integer.parseInt(String.valueOf(rst.get("errcode"))) != WeChatReturnCode.OK_0) {
            return new ResponseDTO(ResultCode.FAIL, "发送失败: " + rst.toString());
        }

        return new ResponseDTO(ResultCode.SUCCESS, "发送成功: " + rst.toString());
    }

    private String getAccessToken() {

//        WechatConfig config = configService.findBySysId(CommonConstant.SYS_ID);
//        if (config == null || StringKit.isEmpty(config.getAccessToken())) {
//            return "";
//        }
//        return config.getAccessToken();

        //部署环境,需带上8080端口访问;外网环境通过80端口访问
        return HttpKit.get("http://wx.cdwmsq1314.com:8080/weChat/weChatAccount/findAccessToken.action").replace("\"", "");
    }
}
