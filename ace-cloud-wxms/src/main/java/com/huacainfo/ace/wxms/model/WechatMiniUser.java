package com.huacainfo.ace.wxms.model;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;

/**
 * 表名：wechat_mini_user
 * 注释：
 *
 * @author HuaCai008
 * @date 2019-10-09
 */
public class WechatMiniUser extends BaseModel {
    /**
     * 字段名：wechat_mini_user.id
     * 注释：唯一主键
     */
    private String id;

    /**
     * 字段名：wechat_mini_user.unionId
     * 注释：微信内,用户唯一标识
     */
    private String unionId;

    /**
     * 字段名：wechat_mini_user.openId
     * 注释：小程序内,用户唯一标识
     */
    private String openId;

    /**
     * 字段名：wechat_mini_user.nickName
     * 注释：用户昵称
     */
    private String nickName;

    /**
     * 字段名：wechat_mini_user.avatarUrl
     * 注释：用户头像
     */
    private String avatarUrl;

    /**
     * 字段名：wechat_mini_user.gender
     * 注释：用户性别,0-未知;1-男;2-女
     */
    private String gender;

    /**
     * 字段名：wechat_mini_user.city
     * 注释：用户所在城市
     */
    private String city;

    /**
     * 字段名：wechat_mini_user.province
     * 注释：用户所在省份
     */
    private String province;

    /**
     * 字段名：wechat_mini_user.country
     * 注释：用户所在国家
     */
    private String country;

    /**
     * 字段名：wechat_mini_user.appId
     * 注释：小程序唯一标识
     */
    private String appId;

    /**
     * 字段名：wechat_mini_user.sysId
     * 注释：系统识别ID
     */
    private String sysId;

    /**
     * 字段名：wechat_mini_user.status
     * 注释：状态码
     */
    private Integer status;

    /**
     * 字段名：wechat_mini_user.remark
     * 注释：备注信息
     */
    private String remark;

    /**
     * 字段名：wechat_mini_user.createTime
     * 注释：入库时间
     */
    private Date createTime;

    /**
     * 字段名：wechat_mini_user.updateTime
     * 注释：更新时间
     */
    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId == null ? null : unionId.trim();
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl == null ? null : avatarUrl.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public String getSysId() {
        return sysId;
    }

    public void setSysId(String sysId) {
        this.sysId = sysId == null ? null : sysId.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
