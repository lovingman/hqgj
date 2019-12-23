package com.huacainfo.ace.wxms.wechat.entity.pojo;

import com.huacainfo.ace.common.model.BaseModel;

/**
 * @ClassName MiniUserInfo
 * @Description TODO
 * @Author Arvin Zou
 * @Date 2019/10/8 14:41
 */
public class MiniUserInfo extends BaseModel {
    /**
     * {
     * "openId": "OPENID",
     * "nickName": "NICKNAME",
     * "gender": GENDER,
     * "city": "CITY",
     * "province": "PROVINCE",
     * "country": "COUNTRY",
     * "avatarUrl": "AVATARURL",
     * "unionId": "UNIONID",
     * "watermark":
     * {
     * "appid":"APPID",
     * "timestamp":TIMESTAMP
     * }
     * }
     */

    /**
     * 小程序内,用户唯一标识
     */
    private String openId;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 用户性别,0-未知;1-男;2-女
     */
    private String gender;
    /**
     * 用户所在城市
     */
    private String city;
    /**
     * 用户所在省份
     */
    private String province;
    /**
     * 用户所在国家
     */
    private String country;
    /**
     * 用户头像
     */
    private String avatarUrl;
    /**
     * 微信内,用户唯一标识
     */
    private String unionId;
    /**
     * 数据水印( watermark )
     */
    private Watermark watermark;

    /**
     * 系统账户信息
     */
    private String sysAccount;

    public String getSysAccount() {
        return sysAccount;
    }

    public void setSysAccount(String sysAccount) {
        this.sysAccount = sysAccount;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public Watermark getWatermark() {
        return watermark;
    }

    public void setWatermark(Watermark watermark) {
        this.watermark = watermark;
    }

    public static class Watermark {
        private String appid;
        private String timestamp;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }
    }
}
