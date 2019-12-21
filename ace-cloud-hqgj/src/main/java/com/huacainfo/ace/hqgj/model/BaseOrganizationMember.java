package com.huacainfo.ace.hqgj.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;

/**
 * 表名：base_organization_member
 * 注释：服务机构成员表
 *
 * @author admin
 * @date 2019-12-10
 */
public class BaseOrganizationMember extends BaseModel {
    /**
     * 字段名：base_organization_member.id
     * 注释：主键ID
     */
    private String id;

    /**
     * 字段名：base_organization_member.orgId
     * 注释：服务机构ID（关联base_organization表id）
     */
    private String orgId;

    /**
     * 字段名：base_organization_member.userId
     * 注释：用户ID
     */
    private String  userId;

    /**
     * 字段名：base_organization_member.name
     * 注释：姓名
     */
    private String name;

    /**
     * 字段名：base_organization_member.idCard
     * 注释：身份证号
     */
    private String idCard;

    /**
     * 字段名：base_organization_member.mobile
     * 注释：手机号
     */
    private String mobile;

    /**
     * 字段名：base_organization_member.jobs
     * 注释：职位
     */
    private String jobs;

    /**
     * 字段名：base_organization_member.speciality
     * 注释：擅长领域
     */
    private String speciality;

    /**
     * 字段名：base_organization_member.content
     * 注释：内容简介
     */
    private String content;

    /**
     * 字段名：base_organization_member.imagePhoto
     * 注释：形象照URL地址
     */
    private String imagePhoto;

    /**
     * 字段名：base_organization_member.wechatPhoto
     * 注释：微信二维码照片URL地址
     */
    private String wechatPhoto;

    /**
     * 字段名：base_organization_member.createUserId
     * 注释：创建人ID
     */
    private String createUserId;

    /**
     * 字段名：base_organization_member.createUserName
     * 注释：创建人姓名
     */
    private String createUserName;

    /**
     * 字段名：base_organization_member.createDate
     * 注释：创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss ",timezone = "GMT+8")
    private Date createDate;

    /**
     * 字段名：base_organization_member.modifyUserId
     * 注释：更新人ID
     */
    private String modifyUserId;

    /**
     * 字段名：base_organization_member.modifyUserName
     * 注释：更新人姓名
     */
    private String modifyUserName;

    /**
     * 字段名：base_organization_member.modifyDate
     * 注释：修改时间
     */
    private Date modifyDate;

    /**
     * 字段名：base_organization_member.status
     * 注释：状态  1-生效  0-失效
     */
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
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

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality == null ? null : speciality.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getImagePhoto() {
        return imagePhoto;
    }

    public void setImagePhoto(String imagePhoto) {
        this.imagePhoto = imagePhoto == null ? null : imagePhoto.trim();
    }

    public String getWechatPhoto() {
        return wechatPhoto;
    }

    public void setWechatPhoto(String wechatPhoto) {
        this.wechatPhoto = wechatPhoto == null ? null : wechatPhoto.trim();
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
