package com.huacainfo.ace.hqgj.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.huacainfo.ace.common.model.BaseModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 表名：company_appeal
 * 注释：企业诉求表
 *
 * @author dwg
 * @date 2020-02-21
 */
public class CompanyAppeal extends BaseModel {
    /**
     * 字段名：company_appeal.id
     * 注释：主键
     */
    private String id;

    /**
     * 字段名：company_appeal.companyId
     * 注释：企业ID
     */
    private String companyId;

    /**
     * 字段名：company_appeal.companyName
     * 注释：企业名称
     */
    private String companyName;

    /**
     * 字段名：company_appeal.type
     * 注释：诉求类型（1-防疫物资 2-法律服务 3-融资需求 4-其它）
     */
    private String type;

    /**
     * 字段名：company_appeal.companyNumber
     * 注释：企业人数
     */
    private Integer companyNumber;

    /**
     * 字段名：company_appeal.title
     * 注释：诉求标题
     */
    private String title;

    /**
     * 字段名：company_appeal.money
     * 注释：融资需求（万元）
     */
    private BigDecimal money;

    /**
     * 字段名：company_appeal.bank
     * 注释：期望银行
     */
    private String bank;

    /**
     * 字段名：company_appeal.personName
     * 注释：联系人姓名
     */
    private String personName;

    /**
     * 字段名：company_appeal.personTel
     * 注释：联系人电话
     */
    private String personTel;

    /**
     * 字段名：company_appeal.createUserId
     * 注释：创建人ID
     */
    private String createUserId;

    /**
     * 字段名：company_appeal.createUserName
     * 注释：创建人姓名
     */
    private String createUserName;

    /**
     * 字段名：company_appeal.createDate
     * 注释：创建时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createDate;

    /**
     * 字段名：company_appeal.modifyUserId
     * 注释：更新人ID
     */
    private String modifyUserId;

    /**
     * 字段名：company_appeal.modifyUserName
     * 注释：更新人姓名
     */
    private String modifyUserName;

    /**
     * 字段名：company_appeal.modifyDate
     * 注释：修改时间
     */
    private Date modifyDate;

    /**
     * 字段名：company_appeal.status
     * 注释：状态  1-待受理  2-已受理 3-已解决
     */
    private String status;

    /**
     * 字段名：company_appeal.text
     * 注释：诉求描述
     */
    private String text;
    private String category;
    private Integer number;
    private List<CompanyMaterial> companyMaterial ;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getCompanyNumber() {
        return companyNumber;
    }

    public void setCompanyNumber(Integer companyNumber) {
        this.companyNumber = companyNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank == null ? null : bank.trim();
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName == null ? null : personName.trim();
    }

    public String getPersonTel() {
        return personTel;
    }

    public void setPersonTel(String personTel) {
        this.personTel = personTel == null ? null : personTel.trim();
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    public List<CompanyMaterial> getCompanyMaterial() {
        return companyMaterial;
    }

    public void setCompanyMaterial(List<CompanyMaterial> companyMaterial) {
        this.companyMaterial = companyMaterial;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}