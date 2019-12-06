package com.huacainfo.ace.hqgj.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;

/**
 * 表名：base_company
 * 注释：企业表
 *
 * @author heshuang
 * @date 2019-12-06
 */
public class BaseCompany extends BaseModel {
    /**
     * 字段名：base_company.id
     * 注释：主键ID
     */
    private String id;

    /**
     * 字段名：base_company.companyName
     * 注释：企业名称
     */
    private String companyName;

    /**
     * 字段名：base_company.creditCode
     * 注释：统一社会信用代码
     */
    private String creditCode;

    /**
     * 字段名：base_company.legalPerson
     * 注释：法定代表人
     */
    private String legalPerson;

    /**
     * 字段名：base_company.contactPersonName
     * 注释：联系人姓名
     */
    private String contactPersonName;

    /**
     * 字段名：base_company.contactPersonTel
     * 注释：联系人电话
     */
    private String contactPersonTel;

    /**
     * 字段名：base_company.areaCode
     * 注释：企业所属行政区划
     */
    private String areaCode;

    /**
     * 字段名：base_company.companyAddress
     * 注释：企业详细地址
     */
    private String companyAddress;

    /**
     * 字段名：base_company.createUserId
     * 注释：创建人ID
     */
    private String createUserId;

    /**
     * 字段名：base_company.createUserName
     * 注释：创建人姓名
     */
    private String createUserName;

    /**
     * 字段名：base_company.createDate
     * 注释：创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date createDate;

    /**
     * 字段名：base_company.modifyUserId
     * 注释：更新人ID
     */
    private String modifyUserId;

    /**
     * 字段名：base_company.modifyUserName
     * 注释：更新人姓名
     */
    private String modifyUserName;

    /**
     * 字段名：base_company.modifyDate
     * 注释：修改时间
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date modifyDate;

    /**
     * 字段名：base_company.status
     * 注释：状态  1-生效  0-失效
     */
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode == null ? null : creditCode.trim();
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson == null ? null : legalPerson.trim();
    }

    public String getContactPersonName() {
        return contactPersonName;
    }

    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName == null ? null : contactPersonName.trim();
    }

    public String getContactPersonTel() {
        return contactPersonTel;
    }

    public void setContactPersonTel(String contactPersonTel) {
        this.contactPersonTel = contactPersonTel == null ? null : contactPersonTel.trim();
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress == null ? null : companyAddress.trim();
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName == null ? null : createUserName.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(String modifyUserId) {
        this.modifyUserId = modifyUserId == null ? null : modifyUserId.trim();
    }

    public String getModifyUserName() {
        return modifyUserName;
    }

    public void setModifyUserName(String modifyUserName) {
        this.modifyUserName = modifyUserName == null ? null : modifyUserName.trim();
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}