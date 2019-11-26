package com.huacainfo.ace.zcpa.vo;

import com.huacainfo.ace.zcpa.model.BaseVolunteer;
import io.swagger.annotations.ApiModelProperty;

public class BaseVolunteerVo extends BaseVolunteer {
    /**
     * 验证码
     */
    @ApiModelProperty(value = "验证码")
    private String captcha;

    /**
     * 字段名：users.account
     * 注释：账号
     */
    @ApiModelProperty(value = "账号")
    private String account;

    /**
     * 字段名：users.password
     * 注释：密码
     */
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 区域值
     */
    @ApiModelProperty(value = "区域值")
    private String areaName;

    /**
     * 性别值
     */
    private String sexName;

    /**
     * 所属类型值
     */
    private String  userTypeName;

    /**
     * 微信头像
     */
    private String headimgurl;

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getSexName() {
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }

    public String getUserTypeName() {
        return userTypeName;
    }

    public void setUserTypeName(String userTypeName) {
        this.userTypeName = userTypeName;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }
}
