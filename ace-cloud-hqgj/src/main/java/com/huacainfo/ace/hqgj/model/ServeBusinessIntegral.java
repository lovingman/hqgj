package com.huacainfo.ace.hqgj.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;

/**
 * 表名：serve_business_integral
 * 注释：积分表
 *
 * @author heshuang
 * @date 2019-12-09
 */
public class ServeBusinessIntegral extends BaseModel {
    /**
     * 字段名：serve_business_integral.id
     * 注释：主键ID
     */
    private String id;

    /**
     * 字段名：serve_business_integral.companyId
     * 注释：企业ID（关联base_company表id）
     */
    private String companyId;

    /**
     * 字段名：serve_business_integral.companyName
     * 注释：企业名称
     */
    private String companyName;

    /**
     * 字段名：serve_business_integral.getIntegral
     * 注释：获得积分
     */
    private Integer getIntegral;

    /**
     * 字段名：serve_business_integral.surplusIntegral
     * 注释：剩余积分
     */
    private Integer surplusIntegral;

    /**
     * 字段名：serve_business_integral.register
     * 注释：注册方式（1-平台 2 线下）
     */
    private String register;

    /**
     * 字段名：serve_business_integral.createUserId
     * 注释：创建人ID
     */
    private String createUserId;

    /**
     * 字段名：serve_business_integral.createUserName
     * 注释：创建人姓名
     */
    private String createUserName;

    /**
     * 字段名：serve_business_integral.createDate
     * 注释：创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createDate;

    /**
     * 字段名：serve_business_integral.modifyUserId
     * 注释：更新人ID
     */
    private String modifyUserId;

    /**
     * 字段名：serve_business_integral.modifyUserName
     * 注释：更新人姓名
     */
    private String modifyUserName;

    /**
     * 字段名：serve_business_integral.modifyDate
     * 注释：修改时间
     */
    private Date modifyDate;

    /**
     * 字段名：serve_business_integral.status
     * 注释：状态  1-生效  0-失效
     */
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public Integer getGetIntegral() {
        return getIntegral;
    }

    public void setGetIntegral(Integer getIntegral) {
        this.getIntegral = getIntegral;
    }

    public Integer getSurplusIntegral() {
        return surplusIntegral;
    }

    public void setSurplusIntegral(Integer surplusIntegral) {
        this.surplusIntegral = surplusIntegral;
    }

    public String getRegister() {
        return register;
    }

    public void setRegister(String register) {
        this.register = register == null ? null : register.trim();
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