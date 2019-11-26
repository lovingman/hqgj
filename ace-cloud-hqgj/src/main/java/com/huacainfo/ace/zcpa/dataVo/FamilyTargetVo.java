package com.huacainfo.ace.zcpa.dataVo;



import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;


public class FamilyTargetVo{
private static final long serialVersionUID = 1L;
 //被服务对象信息
    private String  personName;
    private String idCard;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date  bingDate;

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

    public Date getBingDate() {
        return bingDate;
    }

    public void setBingDate(Date bingDate) {
        this.bingDate = bingDate;
    }

}
