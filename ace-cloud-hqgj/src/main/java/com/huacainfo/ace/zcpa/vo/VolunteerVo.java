package com.huacainfo.ace.zcpa.vo;

import com.huacainfo.ace.common.model.BaseModel;

public class VolunteerVo extends BaseModel {
    private  String  id;
    private  String  areaCode;
    private  String  projectId;
    private  String  targetId;
    private  String  volunteerId;
    private  String  sex;
    private  String  name;
    private  String  mobile;
    private  String  idCard;
    private  String  userType;
    private  String aidTargetType;
    private  String aidTargetName;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getVolunteerId() {
        return volunteerId;
    }

    public void setVolunteerId(String volunteerId) {
        this.volunteerId = volunteerId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getAidTargetType() {
        return aidTargetType;
    }

    public void setAidTargetType(String aidTargetType) {
        this.aidTargetType = aidTargetType;
    }

    public String getAidTargetName() {
        return aidTargetName;
    }

    public void setAidTargetName(String aidTargetName) {
        this.aidTargetName = aidTargetName;
    }
}
