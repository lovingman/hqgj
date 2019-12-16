package com.huacainfo.ace.hqgj.vo;

import com.huacainfo.ace.hqgj.model.ServeFinance;


public class ServeFinanceVo extends ServeFinance {
private static final long serialVersionUID = 1L;
     private String orgAreaCode;
     private String orgAddress;
     private String orgContent;
     private String orgName;
    public String getOrgAreaCode() {
        return orgAreaCode;
    }

    public void setOrgAreaCode(String orgAreaCode) {
        this.orgAreaCode = orgAreaCode;
    }

    public String getOrgAddress() {
        return orgAddress;
    }

    public void setOrgAddress(String orgAddress) {
        this.orgAddress = orgAddress;
    }

    public String getOrgContent() {
        return orgContent;
    }

    public void setOrgContent(String orgContent) {
        this.orgContent = orgContent;
    }

    @Override
    public String getOrgName() {
        return orgName;
    }

    @Override
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}
