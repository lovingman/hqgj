package com.huacainfo.ace.hqgj.model;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;
import java.util.List;

/**
 * 表名：serve_finance
 * 注释：财税服务表
 *
 * @author heshuang
 * @date 2019-12-10
 */
public class ServeFinance extends BaseModel {
    /**
     * 字段名：serve_finance.id
     * 注释：主键ID
     */
    private String id;

    /**
     * 字段名：serve_finance.orgId
     * 注释：服务机构ID（关联base_organization表id）
     */
    private String orgId;

    /**
     * 字段名：serve_finance.orgName
     * 注释：服务机构名称
     */
    private String orgName;

    /**
     * 字段名：serve_finance.contactPersonName
     * 注释：联系人姓名（如是专家问诊，则是专家姓名）
     */
    private String contactPersonName;

    /**
     * 字段名：serve_finance.contactPersonTel
     * 注释：联系人电话
     */
    private String contactPersonTel;

    /**
     * 字段名：serve_finance.address
     * 注释：地址
     */
    private String address;

    /**
     * 字段名：serve_finance.quota
     * 注释：名额
     */
    private Integer quota;

    /**
     * 字段名：serve_finance.type
     * 注释：类型（1-代理记账 2-财税管理 3-专家问诊）
     */
    private String type;

    /**
     * 字段名：serve_finance.fmUrl
     * 注释：封面URL地址
     */
    private String fmUrl;

    /**
     * 字段名：serve_finance.content
     * 注释：服务介绍内容
     */
    private String content;

    /**
     * 字段名：serve_finance.createUserId
     * 注释：创建人ID
     */
    private String createUserId;

    /**
     * 字段名：serve_finance.createUserName
     * 注释：创建人姓名
     */
    private String createUserName;

    /**
     * 字段名：serve_finance.createDate
     * 注释：创建时间
     */
    private Date createDate;

    /**
     * 字段名：serve_finance.modifyUserId
     * 注释：更新人ID
     */
    private String modifyUserId;

    /**
     * 字段名：serve_finance.modifyUserName
     * 注释：更新人姓名
     */
    private String modifyUserName;

    /**
     * 字段名：serve_finance.modifyDate
     * 注释：修改时间
     */
    private Date modifyDate;

    /**
     * 字段名：serve_finance.status
     * 注释：状态  0-待审核 1-审核通过 2-未通过 3-已上线 4-已下线
     */
    private String status;

    private List<ServeFinanceItem> financeItemList;

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

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getQuota() {
        return quota;
    }

    public void setQuota(Integer quota) {
        this.quota = quota;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getFmUrl() {
        return fmUrl;
    }

    public void setFmUrl(String fmUrl) {
        this.fmUrl = fmUrl == null ? null : fmUrl.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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

    public List<ServeFinanceItem> getFinanceItemList() {
        return financeItemList;
    }

    public void setFinanceItemList(List<ServeFinanceItem> financeItemList) {
        this.financeItemList = financeItemList;
    }
}