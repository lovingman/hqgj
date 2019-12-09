package com.huacainfo.ace.hqgj.model;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;

/**
 * 表名：serve_business_other
 * 注释：创业服务资料清单其它资料表
 *
 * @author heshuang
 * @date 2019-12-09
 */
public class ServeBusinessOther extends BaseModel {
    /**
     * 字段名：serve_business_other.id
     * 注释：主键ID
     */
    private String id;

    /**
     * 字段名：serve_business_other.businessId
     * 注释：创业服务表ID（关联serve_business表ID）
     */
    private String businessId;

    /**
     * 字段名：serve_business_other.name
     * 注释：名称
     */
    private String name;

    /**
     * 字段名：serve_business_other.type
     * 注释：类别（1-租赁合同复印件 2-房产证复印件 3-房东营业执照 4-房屋无偿提供使用证明）
     */
    private String type;

    /**
     * 字段名：serve_business_other.createUserId
     * 注释：创建人ID
     */
    private String createUserId;

    /**
     * 字段名：serve_business_other.createUserName
     * 注释：创建人姓名
     */
    private String createUserName;

    /**
     * 字段名：serve_business_other.createDate
     * 注释：创建时间
     */
    private Date createDate;

    /**
     * 字段名：serve_business_other.modifyUserId
     * 注释：更新人ID
     */
    private String modifyUserId;

    /**
     * 字段名：serve_business_other.modifyUserName
     * 注释：更新人姓名
     */
    private String modifyUserName;

    /**
     * 字段名：serve_business_other.modifyDate
     * 注释：修改时间
     */
    private Date modifyDate;

    /**
     * 字段名：serve_business_other.status
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