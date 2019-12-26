package com.huacainfo.ace.wxms.service.impl;

import com.huacainfo.ace.common.constant.CommonKeys;
import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.common.tools.StringKit;
import com.huacainfo.ace.common.web.controller.RedisTemplate;
import com.huacainfo.ace.wxms.dao.MapWechatSysDao;
import com.huacainfo.ace.wxms.dao.WechatMiniUserDao;
import com.huacainfo.ace.wxms.model.MapWechatSys;
import com.huacainfo.ace.wxms.model.WechatConfig;
import com.huacainfo.ace.wxms.model.WechatMiniUser;
import com.huacainfo.ace.wxms.service.MiniAppService;
import com.huacainfo.ace.wxms.service.WechatConfigService;
import com.huacainfo.ace.wxms.wechat.api.MiniAppApi;
import com.huacainfo.ace.wxms.wechat.constant.WxApiRtnCode;
import com.huacainfo.ace.wxms.wechat.entity.pojo.MiniAppAuthData;
import com.huacainfo.ace.wxms.wechat.entity.pojo.MiniUserInfo;
import com.huacainfo.ace.wxms.wechat.entity.pojo.resp.Code2SessionResp;
import com.huacainfo.ace.wxms.wechat.util.HttpKit;
import com.huacainfo.ace.wxms.wechat.util.Sha1Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName MiniAppServiceImpl
 * @Description TODO
 * @Author Arvin Zou
 * @Date 2019/10/8 15:44
 */
@Service
public class MiniAppServiceImpl extends RedisTemplate implements MiniAppService {

    @Resource
    private WechatMiniUserDao miniUserDao;
    @Resource
    private MapWechatSysDao wechatSysDao;

    @Resource
    private WechatConfigService configService;


    /**
     * 小程序用户信息解密/session登录等
     *
     * @param sysId  分配的系统ID
     * @param jscode 登录时获取的 code；调用 wx.login() 获取 临时登录凭证code
     * @param data   wx.getUserInfo 接口返回参数
     *               rawData,signature,encryptedData,iv 为必须传入参数;其他为可选
     * @return ResponseDTO
     */
    @Override
    public ResponseDTO<MiniUserInfo> authority(String sysId, String jscode, MiniAppAuthData data) throws Exception {
        //1. 获取配置信息
        WechatConfig config = configService.findBySysId(sysId);
        if (config == null) {
            return new ResponseDTO<>(ResultCode.FAIL, "系统ID有误");
        }
        //2. 获取 sessionKey
        Code2SessionResp code2Session = MiniAppApi.authCode2Session(config.getAppId(), config.getAppSecret(), jscode);
        if (code2Session.getErrcode() != WxApiRtnCode.OK_0) {
            return new ResponseDTO<>(ResultCode.FAIL, "sessionKey获取失败: " + code2Session.getErrmsg());
        }
        //使用 sha1( rawData + sessionkey ) 得到字符串，用于校验用户信息，
        String sessionKey = code2Session.getSession_key();
        String sha1Str = Sha1Util.getSha1(data.getRawData() + sessionKey);
        if (!sha1Str.equals(data.getSignature())) {
            return new ResponseDTO<>(ResultCode.FAIL, "签名数据不对称");
        }
        //3. 解密用户信息
        MiniUserInfo userInfo = MiniAppApi.decryptUserInfo(sessionKey, data.getEncryptedData(), data.getIv());
        if (userInfo == null) {
            return new ResponseDTO<>(ResultCode.FAIL, "用户信息解密异常");
        }
        //未绑定开放平台时,unionId 获取为空,此时填充 openId 为 unionId
        if (StringKit.isEmpty(userInfo.getUnionId())) {
            userInfo.setUnionId(userInfo.getOpenId());
        }
        if (!config.getAppId().equals(userInfo.getWatermark().getAppid())) {
            return new ResponseDTO<>(ResultCode.FAIL, "用户信息和appId不对称");
        }
        //4. 用户信息存库
        saveOrUpdateUserInfo(sysId, config.getAppId(), userInfo);
        //5. 根据unionId 绑定关系,获取 系统账户信息;注意根据自己业务需求适当调整sql查询语句
        //此处操作,由自己独立写逻辑完成, 建议把特权accout组装放到后端服务器完成, 以免被抓包破解
  //      Map<String, Object> userProp = wechatSysDao.findByUnionId(userInfo.getUnionId());
  //     特殊加权处理
  //      String account = CommonKeys.UN_VERIFICATION_TAG + "|" + "username";
  //      userInfo.setSysAccount(account);
        return new ResponseDTO<>(ResultCode.SUCCESS, "SUCCESS", userInfo);
    }

    /**
     * 微信绑定
     *
     * @param data 发送数据
     * @return ResponseDTO
     */
    @Override
    public ResponseDTO bindWechat(MapWechatSys data) {
        WechatConfig config = configService.findBySysId(data.getSysId());
        if (config == null) {
            return new ResponseDTO(ResultCode.FAIL, "未知系统ID");
        }

        data.setId(GUIDUtil.getGUID());
        data.setAppType(config.getAppType());
        data.setStatus(1);
        data.setCreateDate(DateUtil.getNowDate());
        data.setAppId(config.getAppId());
        int i = wechatSysDao.insert(data);
        if (i == 1) {
            return new ResponseDTO(ResultCode.SUCCESS, "绑定成功");
        }
        return new ResponseDTO(ResultCode.FAIL, "绑定失败,请重试");
    }

    /**
     * 用户信息存储or更新
     *
     * @param sysId    系统ID
     * @param appId    小程序唯一id
     * @param userInfo 用户信息
     * @return int
     */
    private int saveOrUpdateUserInfo(String sysId, String appId, MiniUserInfo userInfo) {

        WechatMiniUser user = miniUserDao.findByUnionId(userInfo.getUnionId());

        if (user == null) {
            user = new WechatMiniUser();
            user.setId(GUIDUtil.getGUID());
            user.setUnionId(userInfo.getUnionId());
            user.setOpenId(userInfo.getOpenId());
            user.setNickName(userInfo.getNickName());
            user.setAvatarUrl(userInfo.getAvatarUrl());
            user.setGender(userInfo.getGender());
            user.setCity(userInfo.getCity());
            user.setProvince(userInfo.getProvince());
            user.setCountry(userInfo.getCountry());
            user.setAppId(appId);
            user.setSysId(sysId);
            user.setStatus(1);
            user.setCreateTime(DateUtil.getNowDate());

            return miniUserDao.insert(user);
        } else {
            user.setUnionId(userInfo.getUnionId());
            user.setOpenId(userInfo.getOpenId());
            user.setNickName(userInfo.getNickName());
            user.setAvatarUrl(userInfo.getAvatarUrl());
            user.setGender(userInfo.getGender());
            user.setCity(userInfo.getCity());
            user.setProvince(userInfo.getProvince());
            user.setCountry(userInfo.getCountry());
            user.setAppId(appId);
            user.setSysId(sysId);
            user.setStatus(1);
            user.setUpdateTime(DateUtil.getNowDate());

            return miniUserDao.updateByPrimaryKey(user);
        }


    }


}
