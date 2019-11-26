package com.huacainfo.ace.zcpa.controller;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.StringKit;
import com.huacainfo.ace.common.web.controller.BaseController;
import com.huacainfo.ace.zcpa.model.WechatConfig;
import com.huacainfo.ace.zcpa.plugins.wechat.WeChatJsapi;
import com.huacainfo.ace.zcpa.plugins.wechat.kit.CaptchaKit;
import com.huacainfo.ace.zcpa.plugins.wechat.pojo.Userinfo;
import com.huacainfo.ace.zcpa.service.WechatConfigService;
import com.huacainfo.ace.zcpa.service.WechatTemplateMsgService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * @ClassName WeChatJsapiController
 * @Description 微信网页jsapi控制器
 * @Author Arvin Zou
 * @Date 2019/9/17 16:20
 */
@RestController
@RequestMapping("/www/jsapi")
public class WeChatJsapiController extends BaseController {
    @Resource
    WechatTemplateMsgService wechatTemplateMsgService;
    @Resource
    private WechatConfigService configService;

    /**
     * 第一步：用户同意授权，获取code
     *
     * @param sysId       (必传)静态值,默认分配;后端解析获取AppId
     * @param redirectUri 接收code访问地址
     * @param scope       (必传)true - 不弹出授权页面，直接跳转，只能获取用户openid;
     *                    false - 弹出授权页面，可通过openid拿到昵称、性别、所在地。并且， 即使在未关注的情况下，只要用户授权，也能获取其信息
     * @param state       (可选)重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值，最多128字节
     * @return ResponseDTO
     * @throws IOException
     */
    @GetMapping(value = "/getAuth", produces = "application/json;charset=UTF-8")
    public ResponseDTO getAuth(String sysId,
                               String redirectUri,
                               String scope,
                               String state,
                               HttpServletResponse response) throws IOException {
        //参数校验
        if (!StringKit.areNotEmpty(sysId, scope)) {
            return new ResponseDTO(ResultCode.FAIL, "缺少必要参数");
        }

        //生成重定向地址
        String uri = configService.getAuth(sysId, redirectUri, state, "true".equals(scope));
        //静默跳转
        if (StringKit.isEmpty(uri)) {
            return new ResponseDTO(ResultCode.FAIL, "获取重定向地址失败");
        } else {
            //response.sendRedirect(uri);
            return new ResponseDTO(ResultCode.SUCCESS, "ok", uri);
        }
    }

    /**
     * * 第二步：通过code换取网页授权access_token
     *
     * @param code  code说明 ： code作为换取access_token的票据，每次用户授权带上的code将不一样，code只能使用一次，5分钟未被使用自动过期。
     * @param state 第一步所传递的参数
     * @return ResponseDTO
     * @throws IOException
     */
    @GetMapping(value = "/parseCode", produces = "application/json;charset=UTF-8")
    public ResponseDTO parseCode(String code, String state,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
        //参数校验
        if (!StringKit.areNotEmpty(code, state)) {
            return new ResponseDTO(ResultCode.FAIL, "code获取失败");
        }
        ResponseDTO<Userinfo> rst = configService.parseCode(code, state);
        if (rst.getStatus() == ResultCode.FAIL) {
            return rst;
        }
        return new ResponseDTO(ResultCode.SUCCESS, "SUCCESS", rst.getData());
    }

    /**
     * 获取微信网页开发签名信息
     *
     * @param sysId 系统id
     * @param uri   当前页 uri 地址
     * @return map
     */
    @GetMapping(value = "/getJsapiConfig", produces = "application/json;charset=UTF-8")
    public ResponseDTO getJsapiConfig(String sysId, String uri) {
        if (!StringKit.areNotEmpty(sysId, uri)) {
            return new ResponseDTO(ResultCode.FAIL, "缺少必要参数");
        }
        WechatConfig config = configService.findBySysId(sysId);
        //失败可能原因：1、授权地址配置失败；2、IP白名单未配置
        if (null == config || StringKit.isEmpty(config.getTicket())) {
            return new ResponseDTO(ResultCode.FAIL, "微信配置获取失败");
        }
        //签名用随机字符串
        long timestamp = DateUtil.getDateTime();
        String noncestr = CaptchaKit.createRandomStr(32, 2);
        String sign = WeChatJsapi.getJsapiSign(noncestr, config.getTicket(), DateUtil.getDateTime(), uri);

        //return
        Map<String, Object> rst = new TreeMap<>();
        rst.put("appId", config.getAppId());
        rst.put("timestamp", timestamp);
        rst.put("nonceStr", noncestr);
        rst.put("signature", sign);

        return new ResponseDTO(ResultCode.SUCCESS, "SUCCESS", rst);
    }

    /**
     * 根据系统ID查找 微信配置信息
     *
     * @param sysId 系统ID
     * @return WechatConfig
     */
    @GetMapping(value = "/getConfig", produces = "application/json;charset=UTF-8")
    public ResponseDTO getConfig(String sysId) {
        if (StringKit.isEmpty(sysId)) {
            return new ResponseDTO(ResultCode.FAIL, "缺少必要参数");
        }
        WechatConfig config = configService.findBySysId(sysId);
        if (config == null) {
            return new ResponseDTO(ResultCode.FAIL, "未找到相关数据");
        }
        return new ResponseDTO(ResultCode.SUCCESS, "获取成功", config);
    }

    /**
     * 手动刷新 公众号/小程序 accessToken令牌
     *
     * @param sysId 使用系统ID
     * @return ResponseDTO
     */
    @GetMapping(value = "/refreshConfig", produces = "application/json;charset=UTF-8")
    public ResponseDTO refreshConfig(String sysId, String token) {
        if (StringKit.isEmpty(sysId)) {
            return new ResponseDTO(ResultCode.FAIL, "缺少必要参数");
        }
        //简单权限验证
        if (!"aovnxhxtj06kuxilhwu72ov5m89421yy".equals(token)) {
            return new ResponseDTO(ResultCode.FAIL, "权限不足,无法调用");
        }

        return configService.refreshConfig(sysId);
    }

    @GetMapping(value = "/noticeTest", produces = "application/json;charset=UTF-8")
    public ResponseDTO noticeTest(String token,
                                  String openid,
                                  String first, String remark, String linkUri,
                                  String topic, String timeStr, String contact, String address, String detail) {

        //简单权限验证
        if (!"aovnxhxtj06kuxilhwu72ov5m89421yy".equals(token)) {
            return new ResponseDTO(ResultCode.FAIL, "权限不足,无法调用");
        }

        return wechatTemplateMsgService.notice(openid, first, remark, linkUri, topic, timeStr, contact, address, detail);
    }
}
