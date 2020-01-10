package com.huacainfo.ace.hqgj.vo;

import com.huacainfo.ace.hqgj.model.BaseOrganization;
import com.huacainfo.ace.hqgj.model.BasicAnnex;
import com.huacainfo.ace.hqgj.model.ServeCultivate;
import com.huacainfo.ace.hqgj.model.ServeCultivateEnroll;

import java.util.List;


public class ServeCultivateDetail extends ServeCultivate {
    //剩余名额
    private Integer surplusQuota;
    //当前用户 是否报名
    private Integer isEnroll;
    //是否签到
    private Integer isSign;
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

    public Integer getSurplusQuota() {
        return surplusQuota;
    }

    public void setSurplusQuota(Integer surplusQuota) {
        this.surplusQuota = surplusQuota;
    }

    public Integer getIsEnroll() {
        return isEnroll;
    }

    public void setIsEnroll(Integer isEnroll) {
        this.isEnroll = isEnroll;
    }

    public Integer getIsSign() {
        return isSign;
    }

    public void setIsSign(Integer isSign) {
        this.isSign = isSign;
    }
}
