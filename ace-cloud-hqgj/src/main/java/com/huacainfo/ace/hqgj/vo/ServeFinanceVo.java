package com.huacainfo.ace.hqgj.vo;

import com.huacainfo.ace.hqgj.model.BaseOrganization;
import com.huacainfo.ace.hqgj.model.BaseOrganizationMember;
import com.huacainfo.ace.hqgj.model.ServeFinance;


public class ServeFinanceVo extends ServeFinance {
    private static final long serialVersionUID = 1L;
    private String surplusQuota;
    private String maxPrice;
    private String minPrice;
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

    public String getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }
}
