package com.huacainfo.ace.hqgj.vo;

import com.huacainfo.ace.hqgj.model.BaseOrganization;
import com.huacainfo.ace.hqgj.model.BasicAnnex;
import com.huacainfo.ace.hqgj.model.ServeCultivate;
import com.huacainfo.ace.hqgj.model.ServeCultivateEnroll;

import java.util.List;


public class ServeCultivateDetail extends ServeCultivate {
    private String surplusQuota;
    private BaseOrganization baseOrganization;
    private List<BasicAnnex> basicAnnexList;
    private List<ServeCultivateEnroll> cultivateEnrollList;


    public BaseOrganization getBaseOrganization() {
        return baseOrganization;
    }

    public void setBaseOrganization(BaseOrganization baseOrganization) {
        this.baseOrganization = baseOrganization;
    }

    public List<BasicAnnex> getBasicAnnexList() {
        return basicAnnexList;
    }

    public void setBasicAnnexList(List<BasicAnnex> basicAnnexList) {
        this.basicAnnexList = basicAnnexList;
    }

    public List<ServeCultivateEnroll> getCultivateEnrollList() {
        return cultivateEnrollList;
    }

    public void setCultivateEnrollList(List<ServeCultivateEnroll> cultivateEnrollList) {
        this.cultivateEnrollList = cultivateEnrollList;
    }

    public String getSurplusQuota() {
        return surplusQuota;
    }

    public void setSurplusQuota(String surplusQuota) {
        this.surplusQuota = surplusQuota;
    }
}
