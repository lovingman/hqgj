package com.huacainfo.ace.zcpa.service;

import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.zcpa.model.WechatConfig;
import com.huacainfo.ace.zcpa.plugins.wechat.pojo.Userinfo;

import java.util.List;

/**
 * @ClassName WechatConfigService
 * @Description TODO
 * @Author Arvin Zou
 * @Date 2019/8/29 16:50
 */
public interface WechatConfigService {

    /**
     * 根据系统ID查找 微信配置信息
     *
     * @param sysId 系统ID
     * @return WxCfg
     */
    WechatConfig findBySysId(String sysId);

    /**
     * 获取库中所有有效配置信息
     *
     * @return List<WxCfg>
     */
    List<WechatConfig> findAppList();

    /**
     * 刷新微信令牌
     *
     * @param app 数据实体
     * @return 更新数量
     */
    int refreshAccessToken(WechatConfig app);

    /**
     * 刷新accessToken
     *
     * @param sysId 系统识别ID
     */
    ResponseDTO<WechatConfig> refreshConfig(String sysId);

    /**
     * 第一步：用户同意授权，获取code
     *
     * @param sysId       (必传)静态值,默认分配;后端解析获取AppId
     * @param redirectUri 接收code访问地址
     * @param scope       (必传)true - 不弹出授权页面，直接跳转，只能获取用户openid;
     *                    false - 弹出授权页面，可通过openid拿到昵称、性别、所在地。并且， 即使在未关注的情况下，只要用户授权，也能获取其信息
     * @param state       (可选)重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值，最多128字节
     * @return redirectUri
     */
    String getAuth(String sysId, String redirectUri, String state, boolean scope);

    /**
     * * 第二步：通过code换取网页授权access_token
     *
     * @param code  code说明 ： code作为换取access_token的票据，每次用户授权带上的code将不一样，code只能使用一次，5分钟未被使用自动过期。
     * @param state 第一步所传递的参数
     * @return ResponseDTO
     */
    ResponseDTO<Userinfo> parseCode(String code, String state);
}
