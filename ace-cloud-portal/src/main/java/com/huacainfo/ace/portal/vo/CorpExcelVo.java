package com.huacainfo.ace.portal.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
public class CorpExcelVo extends BaseRowModel implements java.io.Serializable{

    /**
     * 字段名：corp.pid
     * 注释：上级编号
     */
    @ExcelProperty(value ={"归属单位"},index=3)
    private String pid;

    /**
     * 字段名：corp.name
     * 注释：企事业单位名称
     */
    @ExcelProperty(value ={"单位名称"},index=0)
    private String name;

    /**
     * 字段名：corp.areaCode
     * 注释：行政区划
     */
    @ExcelProperty(value ={"行政区划"},index=2)
    private String areaCode;

    @ExcelProperty(value ={"类型"},index=1)
    private String type;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "CorpExcelVo{" +
                "pid='" + pid + '\'' +
                ", name='" + name + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
