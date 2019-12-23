package com.huacainfo.ace.wxms.controller;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.StringKit;
import com.huacainfo.ace.common.web.controller.BaseController;
import com.huacainfo.ace.wxms.model.WechatConfig;
import com.huacainfo.ace.wxms.service.WechatConfigService;
import com.huacainfo.ace.wxms.wechat.api.WeChatJsapi;
import com.huacainfo.ace.wxms.wechat.entity.pojo.Userinfo;
import com.huacainfo.ace.wxms.wechat.util.CaptchaKit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
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
    private WechatConfigService configService;

    /**
     * 第一步：用户同意授权，获取code
     *
     * @param sysId       (必传)静态值,默认分配;后端解析获取AppId
     * @param redirectUri (必传)重定向跳转地址,需 URL 编码处理,默认本类下 parseCode(...),需写名全路径地址,
     *                    参考 application-dev.yml 下: wechat.jsapi-redirectUri
     * @param scope       (必传)true - 不弹出授权页面，直接跳转，只能获取用户openid;
     *                    false - 弹出授权页面，可通过openid拿到昵称、性别、所在地。并且， 即使在未关注的情况下，只要用户授权，也能获取其信息
     * @param state       (可选)重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值，最多128字节
     * @return ResponseDTO
     * @throws IOException
     */
    @GetMapping(value = "/getAuth", produces = "application/json;charset=UTF-8")
    public ResponseDTO getAuth(String sysId,
                               String redirectUri,
                               String state,
                               String scope,
                               HttpServletResponse response) throws IOException {
        //参数校验
        if (StringKit.isEmpty(sysId)) {
            return new ResponseDTO(ResultCode.FAIL, "缺少系统标识");
        }
        if (StringKit.isEmpty(redirectUri)) {
            return new ResponseDTO(ResultCode.FAIL, "缺少重定向跳转地址");
        }
        if (StringKit.isEmpty(scope)) {
            return new ResponseDTO(ResultCode.FAIL, "缺少授权范围");
        }
        //URL解码
        redirectUri = URLDecoder.decode(redirectUri, "utf-8");
        //state参数 组装
        state = StringKit.isEmpty(state) ? sysId : sysId.concat(",").concat(state);

        //生成重定向地址
        String uri = configService.getAuth(sysId, state, "true".equals(scope), redirectUri);
        //静默跳转
        if (StringKit.isEmpty(uri)) {
            return new ResponseDTO(ResultCode.FAIL, "生成重定向地址失败");
        } else {
            response.sendRedirect(uri);
            return new ResponseDTO(ResultCode.SUCCESS, "SUCCESS");
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
    public ResponseDTO parseCode(String code, String state) {
        //参数校验
        if (!StringKit.areNotEmpty(code, state)) {
            return new ResponseDTO(ResultCode.FAIL, "code获取失败");
        }
        //接口获取用户信息
        ResponseDTO<Userinfo> rst = configService.parseCode(code, state);
        if (rst.getStatus() == ResultCode.FAIL) {
            return new ResponseDTO(ResultCode.FAIL, "获取用户信息失败[" + rst.getMessage() + "]");
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


}
