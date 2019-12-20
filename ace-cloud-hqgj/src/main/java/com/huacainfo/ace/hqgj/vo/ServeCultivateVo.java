package com.huacainfo.ace.hqgj.vo;

import com.huacainfo.ace.hqgj.model.ServeCultivate;
import com.huacainfo.ace.hqgj.model.ServeCultivateSchedule;

import java.util.List;


public class ServeCultivateVo extends ServeCultivate {
    private static final long serialVersionUID = 1L;
    private String orgName;
    //所需名额
    private String surplusQuota;

    private List<ServeCultivateSchedule> scheduleList;

    public List<ServeCultivateSchedule> getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(List<ServeCultivateSchedule> scheduleList) {
        this.scheduleList = scheduleList;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getSurplusQuota() {
        return surplusQuota;
    }

    public void setSurplusQuota(String surplusQuota) {
        this.surplusQuota = surplusQuota;
    }
}
