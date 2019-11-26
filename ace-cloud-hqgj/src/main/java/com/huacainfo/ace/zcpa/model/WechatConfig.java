package com.huacainfo.ace.zcpa.model;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;

/**
 * 表名：wechat_config
 * 注释：
 *
 * @author HuaCai008
 * @date 2019-09-17
 */
public class WechatConfig extends BaseModel {
    /**
     * 字段名：wechat_config.appId
     * 注释：appId,应用唯一标识
     */
    private String appId;

    /**
     * 字段名：wechat_config.appSecret
     * 注释：appScret,应用秘钥
     */
    private String appSecret;

    /**
     * 字段名：wechat_config.accessToken
     * 注释：accessToken令牌
     */
    private String accessToken;

    /**
     * 字段名：wechat_config.ticket
     * 注释：使用票据
     */
    private String ticket;

    /**
     * 字段名：wechat_config.expiresIn
     * 注释：accessToken有效时长(秒)
     */
    private Integer expiresIn;

    /**
     * 字段名：wechat_config.refreshTime
     * 注释：accessToken令牌刷新时间
     */
    private Date refreshTime;

    /**
     * 字段名：wechat_config.deadTime
     * 注释：accessToken令牌失效截止时间
     */
    private Date deadTime;

    /**
     * 字段名：wechat_config.appName
     * 注释：应用名称
     */
    private String appName;

    /**
     * 字段名：wechat_config.appType
     * 注释：应用类型（app-公众号/miniApp-小程序）
     */
    private String appType;

    /**
     * 字段名：wechat_config.sysId
     * 注释：所属系统id
     */
    private String sysId;

    /**
     * 字段名：wechat_config.status
     * 注释：数据状态: 0-数据失效;1-正在使用;2-获取失败[接口报错]
     */
    private Integer status;

    /**
     * 字段名：wechat_config.remark
     * 注释：备注信息
     */
    private String remark;

    /**
     * 字段名：wechat_config.createTime
     * 注释：创建时间
     */
    private Date createTime;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret == null ? null : appSecret.trim();
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken == null ? null : accessToken.trim();
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket == null ? null : ticket.trim();
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public Date getRefreshTime() {
        return refreshTime;
    }

    public void setRefreshTime(Date refreshTime) {
        this.refreshTime = refreshTime;
    }

    public Date getDeadTime() {
        return deadTime;
    }

    public void setDeadTime(Date deadTime) {
        this.deadTime = deadTime;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName == null ? null : appName.trim();
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType == null ? null : appType.trim();
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
}
