package com.huacainfo.ace.hqgj.vo;

import com.huacainfo.ace.hqgj.model.BaseOrganization;
import com.huacainfo.ace.hqgj.model.BaseOrganizationMember;
import com.huacainfo.ace.hqgj.model.ServeFinance;
import com.huacainfo.ace.hqgj.model.ServeFinanceItem;

import java.util.List;


public class ServeFinanceVo extends ServeFinance {
    private static final long serialVersionUID = 1L;
    //剩余名额
    private String surplusQuota;
    private BaseOrganization baseOrganization;
    private BaseOrganizationMember baseOrganizationMember;
    //剩余积分
    private Integer integral;
    //最大
    private String maxPrice;
    //最小
    private String minPrice;
    //是否预约下单
    private String isOrder;
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

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public String getIsOrder() {
        return isOrder;
    }

    public void setIsOrder(String isOrder) {
        this.isOrder = isOrder;
    }
}
