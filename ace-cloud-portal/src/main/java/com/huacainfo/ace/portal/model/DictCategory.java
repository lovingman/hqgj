package com.huacainfo.ace.portal.model;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;

/**
 * 表名：dict_category
 * 注释：
 *
 * @author HuaCai008
 * @date 2019-05-16
 */
public class DictCategory extends BaseModel {
    /**
     * 字段名：dict_category.categoryId
     * 注释：
     */
    private String categoryId;

    /**
     * 字段名：dict_category.name
     * 注释：
     */
    private String name;

    /**
     * 字段名：dict_category.remark
     * 注释：
     */
    private String remark;

    /**
     * 字段名：dict_category.syid
     * 注释：系统编号
     */
    private String syid;

    /**
     * 字段名：dict_category.createTime
     * 注释：
     */
    private Date createTime;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId == null ? null : categoryId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getSyid() {
        return syid;
    }

    public void setSyid(String syid) {
        this.syid = syid == null ? null : syid.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
