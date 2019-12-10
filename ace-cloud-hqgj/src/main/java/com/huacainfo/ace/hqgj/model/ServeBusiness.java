package com.huacainfo.ace.hqgj.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;

/**
 * 表名：serve_business
 * 注释：创业服务表
 *
 * @author heshuang
 * @date 2019-12-09
 */
public class ServeBusiness extends BaseModel {
    /**
     * 字段名：serve_business.id
     * 注释：主键ID
     */
    private String id;

    /**
     * 流水编号
     */
    private String lsNo;

    /**
     * 字段名：serve_business.applyPersonName
     * 注释：申请人姓名
     */
    private String applyPersonName;

    /**
     * 字段名：serve_business.applyPersonTel
     * 注释：申请人联系电话
     */
    private String applyPersonTel;

    /**
     * 字段名：serve_business.idCard
     * 注释：身份证号
     */
    private String idCard;

    /**
     * 字段名：serve_business.companyName
     * 注释：企业名称
     */
    private String companyName;

    /**
     * 字段名：serve_business.readyName
     * 注释：备用名
     */
    private String readyName;

    /**
     * 字段名：serve_business.legalPerson
     * 注释：法定代表人
     */
    private String legalPerson;

    /**
     * 字段名：serve_business.regBonus
     * 注释：注册资金
     */
    private String regBonus;

    /**
     * 字段名：serve_business.areaCode
     * 注释：企业所属行政区划
     */
    private String areaCode;

    /**
     * 字段名：serve_business.companyAddress
     * 注释：企业详细地址
     */
    private String companyAddress;

    /**
     * 字段名：serve_business.manageExtent
     * 注释：经营范围
     */
    private String manageExtent;

    /**
     * 字段名：serve_business.createUserId
     * 注释：创建人ID
     */
    private String createUserId;

    /**
     * 字段名：serve_business.createUserName
     * 注释：创建人姓名
     */
    private String createUserName;

    /**
     * 字段名：serve_business.createDate
     * 注释：创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createDate;

    /**
     * 字段名：serve_business.modifyUserId
     * 注释：更新人ID
     */
    private String modifyUserId;

    /**
     * 字段名：serve_business.modifyUserName
     * 注释：更新人姓名
     */
    private String modifyUserName;

    /**
     * 字段名：serve_business.modifyDate
     * 注释：修改时间
     */
    private Date modifyDate;

    /**
     * 字段名：serve_business.status
     * 注释：状态  0-未审核  1-已审核  
     */
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getApplyPersonName() {
        return applyPersonName;
    }

    public void setApplyPersonName(String applyPersonName) {
        this.applyPersonName = applyPersonName == null ? null : applyPersonName.trim();
    }

    public String getApplyPersonTel() {
        return applyPersonTel;
    }

    public void setApplyPersonTel(String applyPersonTel) {
        this.applyPersonTel = applyPersonTel == null ? null : applyPersonTel.trim();
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getReadyName() {
        return readyName;
    }

    public void setReadyName(String readyName) {
        this.readyName = readyName == null ? null : readyName.trim();
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson == null ? null : legalPerson.trim();
    }

    public String getRegBonus() {
        return regBonus;
    }

    public void setRegBonus(String regBonus) {
        this.regBonus = regBonus == null ? null : regBonus.trim();
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

    public String getManageExtent() {
        return manageExtent;
    }

    public void setManageExtent(String manageExtent) {
        this.manageExtent = manageExtent == null ? null : manageExtent.trim();
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

    public String getLsNo() {
        return lsNo;
    }

    public void setLsNo(String lsNo) {
        this.lsNo = lsNo;
    }
}