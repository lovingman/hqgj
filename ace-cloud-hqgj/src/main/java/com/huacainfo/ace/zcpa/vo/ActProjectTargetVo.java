package com.huacainfo.ace.zcpa.vo;

import com.huacainfo.ace.zcpa.model.ActProjectTarget;
import com.huacainfo.ace.zcpa.model.ActProjectVolunteer;

import java.util.List;


public class ActProjectTargetVo extends ActProjectTarget {
private static final long serialVersionUID = 1L;

  private List<ActProjectVolunteerVo> actProjectVolunteer;

    //服务学校信息
    private String phone;
    private String schoolName;
    private String unicode;
    private String detailAddr;

    public List<ActProjectVolunteerVo> getActProjectVolunteer() {
        return actProjectVolunteer;
    }

    public void setActProjectVolunteer(List<ActProjectVolunteerVo> actProjectVolunteer) {
        this.actProjectVolunteer = actProjectVolunteer;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
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
}
