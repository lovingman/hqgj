package com.huacainfo.ace.zcpa.model;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;

/**
 * 表名：base_social_org
 * 注释：志愿者社会组织
 *
 * @author admin
 * @date 2019-09-25
 */
public class BaseSocialOrg extends BaseModel {
    /**
     * 字段名：base_social_org.id
     * 注释：主键
     */
    private String id;

    /**
     * 字段名：base_social_org.orgName
     * 注释：组织名称
     */
    private String orgName;

    /**
     * 字段名：base_social_org.uniCreditCode
     * 注释：统一信用代码
     */
    private String uniCreditCode;

    /**
     * 字段名：base_social_org.category
     * 注释：类型
     */
    private String category;

    /**
     * 字段名：base_social_org.phone
     * 注释：联系电话
     */
    private String phone;

    /**
     * 字段名：base_social_org.dutyName
     * 注释：负责人姓名
     */
    private String dutyName;

    /**
     * 字段名：base_social_org.areaCode
     * 注释：归属区划
     */
    private String areaCode;

    /**
     * 字段名：base_social_org.detailAddr
     * 注释：详细地址
     */
    private String detailAddr;

    /**
     * 字段名：base_social_org.remark
     * 注释：备注
     */
    private String remark;

    /**
     * 字段名：base_social_org.status
     * 注释：状态
     */
    private String status;

    /**
     * 字段名：base_social_org.createUserId
     * 注释：创建人编号
     */
    private String createUserId;

    /**
     * 字段名：base_social_org.createUserName
     * 注释：创建人姓名
     */
    private String createUserName;

    /**
     * 字段名：base_social_org.createDate
     * 注释：创建日期
     */
    private Date createDate;

    /**
     * 字段名：base_social_org.lastModifyUserId
     * 注释：更新人编号
     */
    private String lastModifyUserId;

    /**
     * 字段名：base_social_org.lastModifyUserName
     * 注释：更新人名称
     */
    private String lastModifyUserName;

    /**
     * 字段名：base_social_org.lastModifyDate
     * 注释：更新日期
     */
    private Date lastModifyDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getUniCreditCode() {
        return uniCreditCode;
    }

    public void setUniCreditCode(String uniCreditCode) {
        this.uniCreditCode = uniCreditCode;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDutyName() {
        return dutyName;
    }

    public void setDutyName(String dutyName) {
        this.dutyName = dutyName;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getDetailAddr() {
        return detailAddr;
    }

    public void setDetailAddr(String detailAddr) {
        this.detailAddr = detailAddr;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getLastModifyUserId() {
        return lastModifyUserId;
    }

    public void setLastModifyUserId(String lastModifyUserId) {
        this.lastModifyUserId = lastModifyUserId;
    }

    public String getLastModifyUserName() {
        return lastModifyUserName;
    }

    public void setLastModifyUserName(String lastModifyUserName) {
        this.lastModifyUserName = lastModifyUserName;
    }

    public Date getLastModifyDate() {
        return lastModifyDate;
    }

    public void setLastModifyDate(Date lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }
}
