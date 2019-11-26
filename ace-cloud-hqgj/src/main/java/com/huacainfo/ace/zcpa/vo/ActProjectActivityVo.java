package com.huacainfo.ace.zcpa.vo;

import com.huacainfo.ace.zcpa.model.ActProjectActivity;

import java.util.List;


public class ActProjectActivityVo extends ActProjectActivity {
private static final long serialVersionUID = 1L;

private  String personName;
private  String name;
private  String fmUrl;
private  String schoolName;
private  String orgId;
private  String nineteenJiFen;

    private String volunteerName;

    private List<ActActivityAttachVo> activitieDetail;
    private List<ActActivityAttachVo> activitieAbnormal;

    private Integer volHour; //时长
    private Integer isNineteen; //是否19活动   17-21号
    private String areaName;   //区域值
    private  String projectName;
    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getNineteenJiFen() {
        return nineteenJiFen;
    }

    public void setNineteenJiFen(String nineteenJiFen) {
        this.nineteenJiFen = nineteenJiFen;
    }

    public String getVolunteerName() {
        return volunteerName;
    }

    public void setVolunteerName(String volunteerName) {
        this.volunteerName = volunteerName;
    }

    public List<ActActivityAttachVo> getActivitieDetail() {
        return activitieDetail;
    }

    public void setActivitieDetail(List<ActActivityAttachVo> activitieDetail) {
        this.activitieDetail = activitieDetail;
    }

    public List<ActActivityAttachVo> getActivitieAbnormal() {
        return activitieAbnormal;
    }

    public void setActivitieAbnormal(List<ActActivityAttachVo> activitieAbnormal) {
        this.activitieAbnormal = activitieAbnormal;
    }

    public String getFmUrl() {
        return fmUrl;
    }
    public void setFmUrl(String fmUrl) {
        this.fmUrl = fmUrl;
    }

    public int getVolHour() {
        return volHour;
    }

    public void setVolHour(int volHour) {
        this.volHour = volHour;
    }

    public Integer getIsNineteen() {
        return isNineteen;
    }

    public void setIsNineteen(Integer isNineteen) {
        this.isNineteen = isNineteen;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
