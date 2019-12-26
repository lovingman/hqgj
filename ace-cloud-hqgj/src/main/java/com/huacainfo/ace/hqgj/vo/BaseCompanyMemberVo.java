package com.huacainfo.ace.hqgj.vo;

import com.huacainfo.ace.hqgj.model.BaseCompany;
import com.huacainfo.ace.hqgj.model.BaseCompanyMember;


public class BaseCompanyMemberVo extends BaseCompanyMember {
private static final long serialVersionUID = 1L;

 private Integer surplusIntegral;

 private String companyName;

    public Integer getSurplusIntegral() {
        return surplusIntegral;
    }

    public void setSurplusIntegral(Integer surplusIntegral) {
        this.surplusIntegral = surplusIntegral;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
