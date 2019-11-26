package com.huacainfo.ace.zcpa.vo;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.List;

public class TargetVo extends BaseModel {
    private String id;
    private String areaCode;
    private String projectId;
    private String targetId;

    private String phone; //電話
    private String targetName;  //被服务名字
    private String unicode;  //统一代码
    private String detailAddr; //详细地址

    private String psex;  //性别
    private String pidCard; //身份证
    private String pcateogry;//类别

    private  List<VolunteerVo> volunteerVo;
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

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public String getUnicode() {
        return unicode;
    }

    public void setUnicode(String unicode) {
        this.unicode = unicode;
    }

    public String getDetailAddr() {
        return detailAddr;
    }

    public void setDetailAddr(String detailAddr) {
        this.detailAddr = detailAddr;
    }

    public String getPsex() {
        return psex;
    }

    public void setPsex(String psex) {
        this.psex = psex;
    }

    public String getPidCard() {
        return pidCard;
    }

    public void setPidCard(String pidCard) {
        this.pidCard = pidCard;
    }

    public String getPcateogry() {
        return pcateogry;
    }

    public void setPcateogry(String pcateogry) {
        this.pcateogry = pcateogry;
    }

    public List<VolunteerVo> getVolunteerVo() {
        return volunteerVo;
    }

    public void setVolunteerVo(List<VolunteerVo> volunteerVo) {
        this.volunteerVo = volunteerVo;
    }

}
