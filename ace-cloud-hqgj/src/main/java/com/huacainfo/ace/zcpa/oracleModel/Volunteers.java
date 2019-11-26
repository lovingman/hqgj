package com.huacainfo.ace.zcpa.oracleModel;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;

public class Volunteers  extends BaseModel {
    /**
     *  唯一值id
     */
    private String id;

    /**
     *区域id
     */
    private String orgId;

    /**
     * 区域code
     */
    private  String  orginternalCode;

    /**
     * 姓名
     */
    private String  name;

    /**
     * 身份证
     */
    private String idCard;

    /**
     * 手机号
     */
    private String mobile;
    /**
     * 所属组织
     */
    private String societyId;



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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSocietyId() {
        return societyId;
    }

    public void setSocietyId(String societyId) {
        this.societyId = societyId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
