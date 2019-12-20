package com.huacainfo.ace.hqgj.vo;

import com.huacainfo.ace.hqgj.model.BaseOrganization;
import com.huacainfo.ace.hqgj.model.BaseOrganizationMember;
import com.huacainfo.ace.hqgj.model.ServeFinance;
import com.huacainfo.ace.hqgj.model.ServeFinanceItem;

import java.util.List;


public class ServeFinanceVo extends ServeFinance {
    private static final long serialVersionUID = 1L;
    private String surplusQuota;
    private BaseOrganization baseOrganization;
    private BaseOrganizationMember baseOrganizationMember;
    //最大
    private String maxPrice;
    //最小
    private String minPrice;
    //项目价格
    private List<ServeFinanceItem> financeItemList;

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

    @Override
    public List<ServeFinanceItem> getFinanceItemList() {
        return financeItemList;
    }

    @Override
    public void setFinanceItemList(List<ServeFinanceItem> financeItemList) {
        this.financeItemList = financeItemList;
    }
}
