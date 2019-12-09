package com.huacainfo.ace.hqgj.model;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;

/**
 * 表名：serve_cultivate_enroll
 * 注释：报名表
 *
 * @author heshuang
 * @date 2019-12-09
 */
public class ServeCultivateEnroll extends BaseModel {
    /**
     * 字段名：serve_cultivate_enroll.id
     * 注释：主键ID
     */
    private String id;

    /**
     * 字段名：serve_cultivate_enroll.serveCultivateId
     * 注释：培训提升基础表ID（关联serve_cultivate表id）
     */
    private String serveCultivateId;

    /**
     * 字段名：serve_cultivate_enroll.companyId
     * 注释：公司ID
     */
    private String companyId;

    /**
     * 字段名：serve_cultivate_enroll.companyName
     * 注释：企业名称
     */
    private String companyName;

    /**
     * 字段名：serve_cultivate_enroll.enrollUserId
     * 注释：报名人员ID
     */
    private String enrollUserId;

    /**
     * 字段名：serve_cultivate_enroll.enrollName
     * 注释：报名人员姓名
     */
    private String enrollName;

    /**
     * 字段名：serve_cultivate_enroll.enrollMobile
     * 注释：报名人员手机号码
     */
    private String enrollMobile;

    /**
     * 字段名：serve_cultivate_enroll.sex
     * 注释：性别 1-男 2-妇女
     */
    private String sex;

    /**
     * 字段名：serve_cultivate_enroll.isSign
     * 注释：是否签到 n-否 y-是
     */
    private String isSign;

    /**
     * 字段名：serve_cultivate_enroll.createUserId
     * 注释：创建人ID
     */
    private String createUserId;

    /**
     * 字段名：serve_cultivate_enroll.createUserName
     * 注释：创建人姓名
     */
    private String createUserName;

    /**
     * 字段名：serve_cultivate_enroll.createDate
     * 注释：创建时间
     */
    private Date createDate;

    /**
     * 字段名：serve_cultivate_enroll.modifyUserId
     * 注释：更新人ID
     */
    private String modifyUserId;

    /**
     * 字段名：serve_cultivate_enroll.modifyUserName
     * 注释：更新人姓名
     */
    private String modifyUserName;

    /**
     * 字段名：serve_cultivate_enroll.modifyDate
     * 注释：修改时间
     */
    private Date modifyDate;

    /**
     * 字段名：serve_cultivate_enroll.status
     * 注释：状态  1-生效 0-失效
     */
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getServeCultivateId() {
        return serveCultivateId;
    }

    public void setServeCultivateId(String serveCultivateId) {
        this.serveCultivateId = serveCultivateId == null ? null : serveCultivateId.trim();
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

    public String getEnrollUserId() {
        return enrollUserId;
    }

    public void setEnrollUserId(String enrollUserId) {
        this.enrollUserId = enrollUserId == null ? null : enrollUserId.trim();
    }

    public String getEnrollName() {
        return enrollName;
    }

    public void setEnrollName(String enrollName) {
        this.enrollName = enrollName == null ? null : enrollName.trim();
    }

    public String getEnrollMobile() {
        return enrollMobile;
    }

    public void setEnrollMobile(String enrollMobile) {
        this.enrollMobile = enrollMobile == null ? null : enrollMobile.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getIsSign() {
        return isSign;
    }

    public void setIsSign(String isSign) {
        this.isSign = isSign == null ? null : isSign.trim();
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