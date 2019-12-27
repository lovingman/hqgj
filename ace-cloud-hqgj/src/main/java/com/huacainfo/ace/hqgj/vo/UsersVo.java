package com.huacainfo.ace.hqgj.vo;

import com.huacainfo.ace.common.security.model.Users;

public class UsersVo extends Users {
    private static final long serialVersionUID = 1L;
    //机构id
    private String orgId;
    //机构名称
    private String orgName;
    //企业id
    private String companyId;
    //企业名称
    private  String companyName;
    //微信用户头像
    private String avatarUrl;

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
}
