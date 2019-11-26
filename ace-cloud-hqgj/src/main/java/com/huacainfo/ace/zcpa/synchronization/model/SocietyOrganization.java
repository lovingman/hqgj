package com.huacainfo.ace.zcpa.synchronization.model;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;

public class SocietyOrganization extends BaseModel {
    /**
     * id唯一值
     */
   private String id;
    /**
     * 组织名称
     */
   private String name;
    /**
     * 办公地址
     */
   private String address;
    /**
     * 负责人
     */
   private String legalPerson;
    /**
     * 联系方式
     */
   private String legalPersonTelephone;
    /**
     * 组织机构代码
     */
   private String registrationNumber;
    /**
     * 组织类别
     */
   private String type;
    /**
     * 部门编号
     */
   private String  departmentNo;
    /**
     * 部门code
     */
   private String orgInternalCode;
    /**
     * 部门id
     */
   private String orgId;

   private Date updateDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getLegalPersonTelephone() {
        return legalPersonTelephone;
    }

    public void setLegalPersonTelephone(String legalPersonTelephone) {
        this.legalPersonTelephone = legalPersonTelephone;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDepartmentNo() {
        return departmentNo;
    }

    public void setDepartmentNo(String departmentNo) {
        this.departmentNo = departmentNo;
    }

    public String getOrgInternalCode() {
        return orgInternalCode;
    }

    public void setOrgInternalCode(String orgInternalCode) {
        this.orgInternalCode = orgInternalCode;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
