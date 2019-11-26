package com.huacainfo.ace.zcpa.vo;

import com.huacainfo.ace.zcpa.model.ActProjectVolunteer;


public class ActProjectVolunteerVo extends ActProjectVolunteer {
private static final long serialVersionUID = 1L;
    private String name;
    private String idCard;
    private String mobile;
    private String userType;
    private String sex;

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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }


    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
