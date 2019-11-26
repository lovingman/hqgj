package com.huacainfo.ace.zcpa.dataVo;



import com.fasterxml.jackson.annotation.JsonFormat;
import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;
import java.util.List;


public class FamilyTargetListVo extends BaseModel {
 //被服务对象信息//姓名
    private String  personName;
    //身份证
    private String idCard;
    //绑定日期
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date  bingDate;

    private List<FamilyVolunteerVo> familyVolunteerVo; //志愿者列表
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

    public List<FamilyVolunteerVo> getFamilyVolunteerVo() {
        return familyVolunteerVo;
    }

    public void setFamilyVolunteerVo(List<FamilyVolunteerVo> familyVolunteerVo) {
        this.familyVolunteerVo = familyVolunteerVo;
    }
}
