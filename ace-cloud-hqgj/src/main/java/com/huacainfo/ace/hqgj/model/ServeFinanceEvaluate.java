package com.huacainfo.ace.hqgj.model;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;

/**
 * 表名：serve_finance_evaluate
 * 注释：财税服务订单评价表
 *
 * @author heshuang
 * @date 2019-12-12
 */
public class ServeFinanceEvaluate extends BaseModel {
    /**
     * 字段名：serve_finance_evaluate.id
     * 注释：主键ID
     */
    private String id;

    /**
     * 字段名：serve_finance_evaluate.orderNo
     * 注释：订单号
     */
    private String orderNo;

    /**
     * 字段名：serve_finance_evaluate.type
     * 注释：类型（1-代理记账 2-财税管理 3-专家问诊）
     */
    private String type;

    /**
     * 字段名：serve_finance_evaluate.orgId
     * 注释：服务机构ID（关联base_organization表id）
     */
    private String orgId;

    /**
     * 字段名：serve_finance_evaluate.orgName
     * 注释：服务机构名称
     */
    private String orgName;

    /**
     * 字段名：serve_finance_evaluate.orgPersonName
     * 注释：服务机构联系人姓名（如是专家问诊，则是专家姓名）
     */
    private String orgPersonName;

    /**
     * 字段名：serve_finance_evaluate.orgPersonTel
     * 注释：服务机构联系人电话
     */
    private String orgPersonTel;

    /**
     * 字段名：serve_finance_evaluate.companyId
     * 注释：企业ID（关联base_company表id）
     */
    private String companyId;

    /**
     * 字段名：serve_finance_evaluate.companyName
     * 注释：企业名称（下单企业名称）
     */
    private String companyName;

    /**
     * 字段名：serve_finance_evaluate.companyPersonName
     * 注释：企业联系人姓名
     */
    private String companyPersonName;

    /**
     * 字段名：serve_finance_evaluate.companyPersonTel
     * 注释：企业联系人电话
     */
    private String companyPersonTel;

    /**
     * 字段名：serve_finance_evaluate.evaluateContent
     * 注释：评价内容
     */
    private String evaluateContent;

    /**
     * 字段名：serve_finance_evaluate.evaluateGrade
     * 注释：服务评分（最高分5分）
     */
    private Integer evaluateGrade;

    /**
     * 字段名：serve_finance_evaluate.address
     * 注释：地址
     */
    private String address;

    /**
     * 字段名：serve_finance_evaluate.quota
     * 注释：名额
     */
    private Integer quota;

    /**
     * 字段名：serve_finance_evaluate.fmUrl
     * 注释：封面URL地址
     */
    private String fmUrl;

    /**
     * 字段名：serve_finance_evaluate.content
     * 注释：服务介绍内容
     */
    private String content;

    /**
     * 字段名：serve_finance_evaluate.createUserId
     * 注释：创建人ID
     */
    private String createUserId;

    /**
     * 字段名：serve_finance_evaluate.createUserName
     * 注释：创建人姓名
     */
    private String createUserName;

    /**
     * 字段名：serve_finance_evaluate.createDate
     * 注释：创建时间
     */
    private Date createDate;

    /**
     * 字段名：serve_finance_evaluate.modifyUserId
     * 注释：更新人ID
     */
    private String modifyUserId;

    /**
     * 字段名：serve_finance_evaluate.modifyUserName
     * 注释：更新人姓名
     */
    private String modifyUserName;

    /**
     * 字段名：serve_finance_evaluate.modifyDate
     * 注释：修改时间
     */
    private Date modifyDate;

    /**
     * 字段名：serve_finance_evaluate.status
     * 注释：状态  1-生效 0-失效
     */
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
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

    public String getOrgPersonName() {
        return orgPersonName;
    }

    public void setOrgPersonName(String orgPersonName) {
        this.orgPersonName = orgPersonName == null ? null : orgPersonName.trim();
    }

    public String getOrgPersonTel() {
        return orgPersonTel;
    }

    public void setOrgPersonTel(String orgPersonTel) {
        this.orgPersonTel = orgPersonTel == null ? null : orgPersonTel.trim();
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

    public String getCompanyPersonName() {
        return companyPersonName;
    }

    public void setCompanyPersonName(String companyPersonName) {
        this.companyPersonName = companyPersonName == null ? null : companyPersonName.trim();
    }

    public String getCompanyPersonTel() {
        return companyPersonTel;
    }

    public void setCompanyPersonTel(String companyPersonTel) {
        this.companyPersonTel = companyPersonTel == null ? null : companyPersonTel.trim();
    }

    public String getEvaluateContent() {
        return evaluateContent;
    }

    public void setEvaluateContent(String evaluateContent) {
        this.evaluateContent = evaluateContent == null ? null : evaluateContent.trim();
    }

    public Integer getEvaluateGrade() {
        return evaluateGrade;
    }

    public void setEvaluateGrade(Integer evaluateGrade) {
        this.evaluateGrade = evaluateGrade;
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
}