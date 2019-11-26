package com.huacainfo.ace.zcpa.model;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;

/**
 * 表名：wechat_user
 * 注释：
 *
 * @author HuaCai008
 * @date 2019-09-18
 */
public class WechatUser extends BaseModel {
    /**
     * 字段名：wechat_user.unionid
     * 注释：微信应用内,用户唯一标识;需绑定开放平台才能获取
     */
    private String unionid;

    /**
     * 字段名：wechat_user.openid
     * 注释：公众号内,用户唯一标识
     */
    private String openid;

    /**
     * 字段名：wechat_user.nickname
     * 注释：昵称
     */
    private String nickname;

    /**
     * 字段名：wechat_user.headimgurl
     * 注释：用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像）
     */
    private String headimgurl;

    /**
     * 字段名：wechat_user.sex
     * 注释：用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     */
    private String sex;

    /**
     * 字段名：wechat_user.country
     * 注释：国家，如中国为CN
     */
    private String country;

    /**
     * 字段名：wechat_user.province
     * 注释：用户个人资料填写的省份
     */
    private String province;

    /**
     * 字段名：wechat_user.city
     * 注释：普通用户个人资料填写的城市
     */
    private String city;

    /**
     * 字段名：wechat_user.access_token
     * 注释：jsapi 令牌
     */
    private String access_token;

    /**
     * 字段名：wechat_user.refresh_token
     * 注释：jsapi刷新令牌
     */
    private String refresh_token;

    /**
     * 字段名：wechat_user.expires_in
     * 注释：令牌超期时间
     */
    private Long expires_in;

    /**
     * 字段名：wechat_user.appid
     * 注释：公众号唯一标识
     */
    private String appid;

    /**
     * 字段名：wechat_user.sysId
     * 注释：数据归属系统ID
     */
    private String sysId;

    /**
     * 字段名：wechat_user.privilege
     * 注释：用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）
     */
    private String privilege;
    /**
     * 最近更新时间
     */
    private Date updateTime;
    /**
     * 数据创建时间
     */
    private Date createTime;

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid == null ? null : unionid.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl == null ? null : headimgurl.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token == null ? null : access_token.trim();
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token == null ? null : refresh_token.trim();
    }

    public Long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Long expires_in) {
        this.expires_in = expires_in;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid == null ? null : appid.trim();
    }

    public String getSysId() {
        return sysId;
    }

    public void setSysId(String sysId) {
        this.sysId = sysId == null ? null : sysId.trim();
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege == null ? null : privilege.trim();
    }
}
