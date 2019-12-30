package com.huacainfo.ace.hqgj.vo;

import com.huacainfo.ace.common.security.model.Users;

public class UsersVo extends Users {
    private static final long serialVersionUID = 1L;
    //机构id
    private String orgId;
    //机构名称
    private String orgName;
    //机构联系电话
    private String orgTel;
    //企业id
    private String companyId;
    //企业名称
    private  String companyName;
    //微信用户头像
    private String avatarUrl;
   //积分
    private String integral;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getOrgTel() {
        return orgTel;
    }

    public void setOrgTel(String orgTel) {
        this.orgTel = orgTel;
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }
}
