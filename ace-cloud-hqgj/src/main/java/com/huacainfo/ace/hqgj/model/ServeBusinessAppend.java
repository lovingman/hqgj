package com.huacainfo.ace.hqgj.model;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;

/**
 * 表名：serve_business_append
 * 注释：创业服务资料其它附加信息表
 *
 * @author heshuang
 * @date 2019-12-13
 */
public class ServeBusinessAppend extends BaseModel {
    /**
     * 字段名：serve_business_append.id
     * 注释：主键ID
     */
    private String id;

    /**
     * 字段名：serve_business_append.businessId
     * 注释：创业服务表ID（关联serve_business表ID）
     */
    private String businessId;

    /**
     * 字段名：serve_business_append.type
     * 注释：类别 1-申请免费服务项目 2-确认培训意向 3-开户银行 4-是否选择代理记账服务 5-特殊说明
     */
    private String type;

    /**
     * 字段名：serve_business_append.name
     * 注释：类别名称
     */
    private String name;

    /**
     * 字段名：serve_business_append.option
     * 注释：选项值（各类别对应的选项值，如type=1 申请免费服务项目，则 1-工商注册代办服务 2-银行基本开户代办服务 3-银行开户费小额账户管理费等，具体根据字典来）
     */
    private String option;

    /**
     * 字段名：serve_business_append.optionName
     * 注释：选项值名称
     */
    private String optionName;

    /**
     * 字段名：serve_business_append.content
     * 注释：特殊说明内容
     */
    private String content;

    /**
     * 字段名：serve_business_append.createUserId
     * 注释：创建人ID
     */
    private String createUserId;

    /**
     * 字段名：serve_business_append.createUserName
     * 注释：创建人姓名
     */
    private String createUserName;

    /**
     * 字段名：serve_business_append.createDate
     * 注释：创建时间
     */
    private Date createDate;

    /**
     * 字段名：serve_business_append.modifyUserId
     * 注释：更新人ID
     */
    private String modifyUserId;

    /**
     * 字段名：serve_business_append.modifyUserName
     * 注释：更新人姓名
     */
    private String modifyUserName;

    /**
     * 字段名：serve_business_append.modifyDate
     * 注释：修改时间
     */
    private Date modifyDate;

    /**
     * 字段名：serve_business_append.status
     * 注释：状态  1-生效 0-失效
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option == null ? null : option.trim();
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName == null ? null : optionName.trim();
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