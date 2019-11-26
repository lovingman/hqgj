package com.huacainfo.ace.portal.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

public class ProvinceExcelVo extends BaseRowModel implements java.io.Serializable{


    /**
     * 字段名：province.code
     * 注释：
     */
    @ExcelProperty(value ={"行政区划编号"},index=0)
    private String code;

    /**
     * 字段名：province.name
     * 注释：
     */
    @ExcelProperty(value ={"行政区划简称"},index=1)
    private String name;

    /**
     * 字段名：province.type
     * 注释：
     */
    @ExcelProperty(value ={"类型"},index=2)
    private String type;

    /**
     * 字段名：province.parent_code
     * 注释：
     */
    @ExcelProperty(value ={"上级行政区划编号"},index=3)
    private String parentCode;

    /**
     * 字段名：province.fullname
     * 注释：
     */
    @ExcelProperty(value ={"全称"},index=4)
    private String fullname;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
