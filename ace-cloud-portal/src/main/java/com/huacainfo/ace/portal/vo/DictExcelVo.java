package com.huacainfo.ace.portal.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

public class DictExcelVo extends BaseRowModel implements java.io.Serializable{

    @ExcelProperty(value ={"类型"},index=0)
    private String categoryId;


    /**
     * 字段名：corp.areaCode
     * 注释：行政区划
     */
    @ExcelProperty(value ={"编码"},index=1)
    private String code;


    /**
     * 字段名：corp.name
     * 注释：企事业单位名称
     */
    @ExcelProperty(value ={"名称"},index=2)
    private String name;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

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
}
