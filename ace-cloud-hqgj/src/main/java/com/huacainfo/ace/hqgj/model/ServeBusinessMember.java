package com.huacainfo.ace.hqgj.model;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;

/**
 * 表名：serve_business_member
 * 注释：创业服务资料清单人员表
 *
 * @author heshuang
 * @date 2019-12-09
 */
public class ServeBusinessMember extends BaseModel {
    /**
     * 字段名：serve_business_member.id
     * 注释：主键ID
     */
    private String id;

    /**
     * 字段名：serve_business_member.businessId
     * 注释：创业服务表ID（关联serve_business表ID）
     */
    private String businessId;

    /**
     * 字段名：serve_business_member.name
     * 注释：姓名
     */
    private String name;

    /**
     * 字段名：serve_business_member.sex
     * 注释：性别 1-男 2-妇女
     */
    private String sex;

    /**
     * 字段名：serve_business_member.idCard
     * 注释：身份证号
     */
    private String idCard;

    /**
     * 字段名：serve_business_member.mobile
     * 注释：联系电话
     */
    private String mobile;

    /**
     * 字段名：serve_business_member.jobs
     * 注释：职位
     */
    private String jobs;

    /**
     * 字段名：serve_business_member.sharesProportion
     * 注释：持股比例
     */
    private String sharesProportion;

    /**
     * 字段名：serve_business_member.type
     * 注释：类别 1-法人 2-监事 3-股东 4-财务
     */
    private String type;

    /**
     * 字段名：serve_business_member.createUserId
     * 注释：创建人ID
     */
    private String createUserId;

    /**
     * 字段名：serve_business_member.createUserName
     * 注释：创建人姓名
     */
    private String createUserName;

    /**
     * 字段名：serve_business_member.createDate
     * 注释：创建时间
     */
    private Date createDate;

    /**
     * 字段名：serve_business_member.modifyUserId
     * 注释：更新人ID
     */
    private String modifyUserId;

    /**
     * 字段名：serve_business_member.modifyUserName
     * 注释：更新人姓名
     */
    private String modifyUserName;

    /**
     * 字段名：serve_business_member.modifyDate
     * 注释：修改时间
     */
    private Date modifyDate;

    /**
     * 字段名：serve_business_member.status
     * 注释：状态  0-待审  1-通过 2-驳回修改
     */
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId == null ? null : businessId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
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

    public String getSharesProportion() {
        return sharesProportion;
    }

    public void setSharesProportion(String sharesProportion) {
        this.sharesProportion = sharesProportion == null ? null : sharesProportion.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
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