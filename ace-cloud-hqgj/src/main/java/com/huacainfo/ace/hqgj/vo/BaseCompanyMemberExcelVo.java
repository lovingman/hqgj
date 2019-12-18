package com.huacainfo.ace.hqgj.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

public class BaseCompanyMemberExcelVo extends BaseRowModel implements java.io.Serializable {
    @ExcelProperty(value ={"姓名"},index=0)
    private String name;

    /**
     * 字段名：idCard
     * 注释：身份证号
     */
    @ExcelProperty(value ={"身份证号"},index=1)
    private String idCard;

    /**
     * 字段名：mobile
     * 注释：手机号
     */
    @ExcelProperty(value ={"手机号"},index=1)
    private String mobile;

    /**
     * 字段名：jobs
     * 注释：职位
     */
    @ExcelProperty(value ={"职位"},index=1)
    private String jobs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getJobs() {
        return jobs;
    }

    public void setJobs(String jobs) {
        this.jobs = jobs;
    }
}

