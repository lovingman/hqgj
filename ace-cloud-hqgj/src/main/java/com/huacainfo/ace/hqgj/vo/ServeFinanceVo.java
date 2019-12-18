package com.huacainfo.ace.hqgj.vo;

import com.huacainfo.ace.hqgj.model.BaseOrganization;
import com.huacainfo.ace.hqgj.model.BaseOrganizationMember;
import com.huacainfo.ace.hqgj.model.ServeFinance;


public class ServeFinanceVo extends ServeFinance {
    private static final long serialVersionUID = 1L;
    private String surplusQuota;
    private BaseOrganization baseOrganization;
    private BaseOrganizationMember baseOrganizationMember;

    public BaseOrganizationMember getBaseOrganizationMember() {
        return baseOrganizationMember;
    }

    public void setBaseOrganizationMember(BaseOrganizationMember baseOrganizationMember) {
        this.baseOrganizationMember = baseOrganizationMember;
    }


    public BaseOrganization getBaseOrganization() {
        return baseOrganization;
    }

    public void setBaseOrganization(BaseOrganization baseOrganization) {
        this.baseOrganization = baseOrganization;
    }

    public String getSurplusQuota() {
        return surplusQuota;
    }

    public void setSurplusQuota(String surplusQuota) {
        this.surplusQuota = surplusQuota;
    }
}
