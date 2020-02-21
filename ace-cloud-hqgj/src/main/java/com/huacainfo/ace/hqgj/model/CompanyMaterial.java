package com.huacainfo.ace.hqgj.model;

import com.huacainfo.ace.common.model.BaseModel;

/**
 * 表名：company_material
 * 注释：企业物资表
 *
 * @author dwg
 * @date 2020-02-21
 */
public class CompanyMaterial extends BaseModel {
    /**
     * 字段名：company_material.id
     * 注释：主键
     */
    private String id;

    /**
     * 字段名：company_material.appealId
     * 注释：企业诉求表ID（关联company_appeal表ID）
     */
    private String appealId;

    /**
     * 字段名：company_material.category
     * 注释：物资类别（1-口罩 2-体温枪 3-酒精 4-84消毒液 5-无触摸打卡机）
     */
    private String category;

    /**
     * 字段名：company_material.number
     * 注释：数量
     */
    private Integer number;

    /**
     * 字段名：company_material.status
     * 注释：状态 1-生效 0-失效
     */
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAppealId() {
        return appealId;
    }

    public void setAppealId(String appealId) {
        this.appealId = appealId == null ? null : appealId.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}