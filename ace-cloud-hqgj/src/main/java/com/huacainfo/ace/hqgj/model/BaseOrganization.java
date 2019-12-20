package com.huacainfo.ace.hqgj.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;

/**
 * 表名：base_organization
 * 注释：服务机构表
 *
 * @author admin
 * @date 2019-12-09
 */
public class BaseOrganization extends BaseModel {
    /**
     * 字段名：base_organization.id
     * 注释：主键ID
     */
    private String id;

    /**
     * 字段名：base_organization.orgName
     * 注释：机构名称
     */
    private String orgName;

    /**
     * 字段名：base_organization.creditCode
     * 注释：统一社会信用代码
     */
    private String creditCode;

    /**
     * 字段名：base_organization.contactPersonName
     * 注释：联系人姓名
     */
    private String contactPersonName;

    /**
     * 字段名：base_organization.contactPersonTel
     * 注释：联系人电话
     */
    private String contactPersonTel;

    /**
     * 字段名：base_organization.areaCode
     * 注释：机构所属行政区划
     */
    private String areaCode;

    /**
     * 字段名：base_organization.orgAddress
     * 注释：机构详细地址
     */
    private String orgAddress;

    /**
     * 字段名：base_organization.content
     * 注释：内容简介
     */
    private String content;

    /**
     * 字段名：base_organization.type
     * 注释：类型  1-律师事务所 2-会计事务所 3-培训机构
     */
    private String type;

    /**
     * 字段名：base_organization.fmUrl
     * 注释：机构封面URL地址
     */
    private String fmUrl;

    /**
     * 字段名：base_organization.createUserId
     * 注释：创建人ID
     */
    private String createUserId;

    /**
     * 字段名：base_organization.createUserName
     * 注释：创建人姓名
     */
    private String createUserName;

    /**
     * 字段名：base_organization.createDate
     * 注释：创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss ",timezone = "GMT+8")
    private Date createDate;

    /**
     * 字段名：base_organization.modifyUserId
     * 注释：更新人ID
     */
    private String modifyUserId;

    /**
     * 字段名：base_organization.modifyUserName
     * 注释：更新人姓名
     */
    private String modifyUserName;

    /**
     * 字段名：base_organization.modifyDate
     * 注释：修改时间
     */
    private Date modifyDate;

    /**
     * 字段名：base_organization.status
     * 注释：状态  1-生效  0-失效
     */
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode == null ? null : creditCode.trim();
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

    public String getOrgAddress() {
        return orgAddress;
    }

    public void setOrgAddress(String orgAddress) {
        this.orgAddress = orgAddress == null ? null : orgAddress.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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

    public String getFmUrl() {
        return fmUrl;
    }

    public void setFmUrl(String fmUrl) {
        this.fmUrl = fmUrl;
    }
}
