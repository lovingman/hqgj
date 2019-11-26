package com.huacainfo.ace.zcpa.model;

import com.huacainfo.ace.common.model.BaseModel;

/**
 * 表名：province
 * 注释：
 *
 * @author HuaCai008
 * @date 2019-05-17
 */
public class Province extends BaseModel {
    /**
     * 字段名：province.code
     * 注释：
     */
    private String code;

    /**
     * 字段名：province.name
     * 注释：
     */
    private String name;

    /**
     * 字段名：province.type
     * 注释：
     */
    private String type;

    /**
     * 字段名：province.parent_code
     * 注释：
     */
    private String parentCode;

    /**
     * 字段名：province.fullname
     * 注释：
     */
    private String fullname;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
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

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode == null ? null : parentCode.trim();
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname == null ? null : fullname.trim();
    }
}