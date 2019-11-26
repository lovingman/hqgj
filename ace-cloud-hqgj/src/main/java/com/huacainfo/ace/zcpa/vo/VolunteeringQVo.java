package com.huacainfo.ace.zcpa.vo;

import com.huacainfo.ace.zcpa.model.ActProject;


public class VolunteeringQVo extends ActProject {
private static final long serialVersionUID = 1L;
 private String aidTargetType;
 private String areaCode;
 private String projectId;
 private String targetId;
 private String category;

    public String getAidTargetType() {
        return aidTargetType;
    }

    public void setAidTargetType(String aidTargetType) {
        this.aidTargetType = aidTargetType;
    }

    @Override
    public String getAreaCode() {
        return areaCode;
    }

    @Override
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public void setCategory(String category) {
        this.category = category;
    }
}
