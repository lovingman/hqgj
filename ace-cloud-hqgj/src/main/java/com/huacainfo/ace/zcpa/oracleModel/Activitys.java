package com.huacainfo.ace.zcpa.oracleModel;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;

public class Activitys extends BaseModel {
    private String id;
    //区域id
    private String orgId;
    //区域code
    private String orginternalCode;
    //特殊人员类型
    private String personType;
    //特殊人员id
    private String personId;
    //特殊人员姓名
    private String personName;
    //特殊身份证号
    private String idCard;
    //服务时间
    private Date activityTime;
    //地址
    private String areaName;
    //服务人员姓名
    private String name;
    //服务人员身份证
    private String vidCard;
    //服务描述
    private String content;
    //是否完成
    private String complete;
    //创建时间
    private Date createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrginternalCode() {
        return orginternalCode;
    }

    public void setOrginternalCode(String orginternalCode) {
        this.orginternalCode = orginternalCode;
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

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

    public String getComplete() {
        return complete;
    }

    public void setComplete(String complete) {
        this.complete = complete;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
