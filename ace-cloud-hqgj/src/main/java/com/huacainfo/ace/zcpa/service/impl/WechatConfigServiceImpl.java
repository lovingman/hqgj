package com.huacainfo.ace.zcpa.service.impl;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.StringKit;
import com.huacainfo.ace.zcpa.dao.WechatConfigDao;
import com.huacainfo.ace.zcpa.dao.WechatUserDao;
import com.huacainfo.ace.zcpa.model.WechatConfig;
import com.huacainfo.ace.zcpa.model.WechatUser;
import com.huacainfo.ace.zcpa.plugins.wechat.WeChatApi;
import com.huacainfo.ace.zcpa.plugins.wechat.WeChatJsapi;
import com.huacainfo.ace.zcpa.plugins.wechat.constant.WeChatReturnCode;
import com.huacainfo.ace.zcpa.plugins.wechat.pojo.Userinfo;
import com.huacainfo.ace.zcpa.plugins.wechat.pojo.resp.JsapiAccessTokenResp;
import com.huacainfo.ace.zcpa.service.WechatConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName WxCfgServiceImpl
 * @Description TODO
 * @Author Arvin Zou
 * @Date 2019/8/29 16:51
 */
@Service
public class WechatConfigServiceImpl implements WechatConfigService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WechatConfigDao configDao;
    @Autowired
    private WechatUserDao wechatUserDao;

    @Value("${wechat.jsapi-redirectUri}")
    private String redirectUri;

    /**
     * 根据系统ID查找 微信配置信息
     *
     * @param sysId 系统ID
     * @return WechatConfig
     */
    @Override
    public WechatConfig findBySysId(String sysId) {

        return configDao.findBySysId(sysId);
    }

    /**
     * 获取库中所有有效配置信息
     *
     * @return List<WechatConfig>
     */
    @Override
    public List<WechatConfig> findAppList() {
        return configDao.findAppListByStatus(1);
    }

    @Override
    public int refreshAccessToken(WechatConfig wxCfg) {
        //当前系统时间
        Date nowDateTime = DateUtil.getNowDate();

        if (wxCfg.getDeadTime() == null) {
            return updateAccessToken(wxCfg);
        } else {
            //剩余有效时长
            long balanceValidSeconds = DateUtil.getBetween(wxCfg.getDeadTime(), nowDateTime, "Seconds");
            //有效时长小于200s,重新获取accessToken令牌
            if (balanceValidSeconds <= 200) {
                return updateAccessToken(wxCfg);
            }

            return 0;
        }


    }

    /**
     * 刷新accessToken
     *
     * @param sysId 系统识别ID
     */
    @Override
    public ResponseDTO<WechatConfig> refreshConfig(String sysId) {
        WechatConfig wxCfg = this.configDao.findBySysId(sysId);
        if (wxCfg == null) {
            return new ResponseDTO(ResultCode.FAIL, "未识别系统ID");
        }

        int i = updateAccessToken(wxCfg);
        if (i == 1) {
            return new ResponseDTO<>(ResultCode.SUCCESS, "令牌刷新成功");
        }

        return new ResponseDTO<>(ResultCode.FAIL, "令牌刷新失败");
    }

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
    @Override
    public String getAuth(String sysId, String redirectUri, String state, boolean scope) {

        WechatConfig config = findBySysId(sysId);
        if (config == null) {
            return "";
        }
        //state值补充
        state = StringKit.isEmpty(state) ? sysId : sysId + "," + state;
        redirectUri = StringKit.isEmpty(redirectUri) ? this.redirectUri : redirectUri;

        return WeChatJsapi.getAuthUri(config.getAppId(), redirectUri, state, scope);
    }

    /**
     * * 第二步：通过code换取网页授权access_token
     *
     * @param code  code说明 ： code作为换取access_token的票据，每次用户授权带上的code将不一样，code只能使用一次，5分钟未被使用自动过期。
     * @param state 第一步所传递的参数;多个参数值以英文","隔开;第一个默认为sysId
     * @return ResponseDTO
     */
    @Override
    public ResponseDTO<Userinfo> parseCode(String code, String state) {
        //state参数
        String[] params = state.split(",");
        if (params == null || params.length == 0) {
            return new ResponseDTO(ResultCode.FAIL, "state参数解析失败");
        }
        //获取微信配置
        String sysId = params[0];
        WechatConfig config = findBySysId(sysId);
        if (config == null) {
            return new ResponseDTO(ResultCode.FAIL, "未找到相关微信配置");
        }
        //获取网页授权 AccessToken,不同于接口统一令牌
        JsapiAccessTokenResp jsapiAccessToken = WeChatJsapi.getJsapiAccessToken(config.getAppId(), config.getAppSecret(), code);
        if (jsapiAccessToken.getErrcode() != WeChatReturnCode.OK_0) {
            return new ResponseDTO(ResultCode.FAIL, "AccessToken获取失败=>" + jsapiAccessToken.toString());
        }
        //scope 为 "snsapi_userinfo" ,才能获取详细信息
        Userinfo userinfo = WeChatJsapi.getUserinfo(jsapiAccessToken.getAccess_token(), jsapiAccessToken.getOpenid());
        if (userinfo.getErrcode() != WeChatReturnCode.OK_0) {
            return new ResponseDTO(ResultCode.FAIL, "解析用户信息失败=>" + userinfo.toString());
        }
        //未绑定开放平台时,设置 unionid 为openid
        userinfo.setUnionid(StringKit.isEmpty(userinfo.getUnionid()) ? userinfo.getOpenid() : userinfo.getUnionid());

        //按需存储用户信息到本地数据库
        insertOrUpdateWechatUser(userinfo,
                jsapiAccessToken.getAccess_token(), jsapiAccessToken.getRefresh_token(),
                jsapiAccessToken.getExpires_in(), config.getAppId(), sysId);

        return new ResponseDTO(ResultCode.SUCCESS, "OK", userinfo);
    }

    /**
     * 存储微信用户信息到本地数据库
     *
     * @param userinfo 微信用户详细信息
     * @return int
     */
    private int insertOrUpdateWechatUser(Userinfo userinfo,
                                         String accessToken, String refreshToken,
                                         Integer expiresIn, String appid, String sysId) {

        WechatUser user = new WechatUser();
        user.setUnionid(userinfo.getUnionid());
        user.setOpenid(userinfo.getOpenid());
        user.setNickname(userinfo.getNickname());
        user.setHeadimgurl(userinfo.getHeadimgurl());
        user.setSex(userinfo.getSex());
        user.setCountry(userinfo.getCountry());
        user.setProvince(userinfo.getProvince());
        user.setCity(userinfo.getCity());
        user.setPrivilege(userinfo.getPrivilege());
        user.setAccess_token(accessToken);
        user.setRefresh_token(refreshToken);
        user.setExpires_in(expiresIn.longValue());
        user.setAppid(appid);
        user.setSysId(sysId);

        int i = wechatUserDao.isExists(user);
        if (i > 0) {
            user.setUpdateTime(DateUtil.getNowDate());
            return wechatUserDao.updateByPrimaryKey(user);
        } else {
            user.setCreateTime(DateUtil.getNowDate());
            return wechatUserDao.insert(user);
        }
    }

    /**
     * 更新微信相关配置
     *
     * @param app 数据源
     * @return int
     */
    private int updateAccessToken(WechatConfig app) {
        //获取令牌封装对象
        ResponseDTO<WechatConfig> resp = WeChatApi.getAccessToken(app.getAppId(), app.getAppSecret());
        //获取失败标记错误信息
        if (ResultCode.FAIL == resp.getStatus()) {
            app.setRefreshTime(DateUtil.getNowDate());
            app.setRemark(resp.getMessage());
            app.setStatus(2);
            return configDao.updateByPrimaryKey(app);
        }
        //获取成功,更新令牌
        else {
            WechatConfig config = resp.getData();
            app.setAccessToken(config.getAccessToken());
            app.setTicket(config.getTicket());
            app.setExpiresIn(config.getExpiresIn());
            app.setRefreshTime(config.getRefreshTime());
            app.setDeadTime(config.getDeadTime());
            app.setRemark("获取成功");
            return configDao.updateByPrimaryKey(app);
        }

    }
}
