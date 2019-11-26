package com.huacainfo.ace.zcpa.dataVo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;
import java.util.List;

public class ActivityVo extends BaseModel {

    private String activityId;
    //区划
    private String areaCode;
    //被服务对象id
    private String personId;
    //被服务对象信息//姓名
    private String  personName;
    //被服务队形类型
    private String personType;
    //身份证
    private String idCard;
   //服务时间
   @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date activityTime;
    //服务地点
    private String areaName;
    //服务人员姓名
    private String name;
    //服务人员的身份证号
    private String vidCard;
    //服务内容
    private String content;
    //是否完成服务
    private  String isComplete;
    //附件
    private List<String> fileURL;

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Date getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(Date activityTime) {
        this.activityTime = activityTime;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVidCard() {
        return vidCard;
    }

    public void setVidCard(String vidCard) {
        this.vidCard = vidCard;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(String isComplete) {
        this.isComplete = isComplete;
    }

    public List<String> getFileURL() {
        return fileURL;
    }

    public void setFileURL(List<String> fileURL) {
        this.fileURL = fileURL;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }
}
