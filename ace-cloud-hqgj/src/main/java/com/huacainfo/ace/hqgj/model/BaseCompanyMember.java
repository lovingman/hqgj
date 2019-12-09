package com.huacainfo.ace.hqgj.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;

/**
 * 表名：base_company_member
 * 注释：企业成员表
 *
 * @author heshuang
 * @date 2019-12-09
 */
public class BaseCompanyMember extends BaseModel {
    /**
     * 字段名：base_company_member.id
     * 注释：主键ID
     */
    private String id;

    /**
     * 字段名：base_company_member.companyId
     * 注释：企业ID（关联base_company表id）
     */
    private String companyId;

    /**
     * 字段名：base_company_member.name
     * 注释：姓名
     */
    private String name;

    /**
     * 字段名：base_company_member.idCard
     * 注释：身份证号
     */
    private String idCard;

    /**
     * 字段名：base_company_member.mobile
     * 注释：手机号
     */
    private String mobile;

    /**
     * 字段名：base_company_member.jobs
     * 注释：职位
     */
    private String jobs;

    /**
     * 字段名：base_company_member.createUserId
     * 注释：创建人ID
     */
    private String createUserId;

    /**
     * 字段名：base_company_member.createUserName
     * 注释：创建人姓名
     */
    private String createUserName;

    /**
     * 字段名：base_company_member.createDate
     * 注释：创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss ",timezone = "GMT+8")
    private Date createDate;

    /**
     * 字段名：base_company_member.modifyUserId
     * 注释：更新人ID
     */
    private String modifyUserId;

    /**
     * 字段名：base_company_member.modifyUserName
     * 注释：更新人姓名
     */
    private String modifyUserName;

    /**
     * 字段名：base_company_member.modifyDate
     * 注释：修改时间
     */
    private Date modifyDate;

    /**
     * 字段名：base_company_member.status
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getJobs() {
        return jobs;
    }

    public void setJobs(String jobs) {
        this.jobs = jobs == null ? null : jobs.trim();
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
