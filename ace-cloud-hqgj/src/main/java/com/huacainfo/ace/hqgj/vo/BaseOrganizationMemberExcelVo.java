package com.huacainfo.ace.hqgj.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

public class BaseOrganizationMemberExcelVo extends BaseRowModel implements java.io.Serializable {
    @ExcelProperty(value ={"姓名"},index=0)
    private String name;


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

    /**
     * 字段名：content
     * 注释：内容简介
     */
    @ExcelProperty(value ={"内容简介"},index=1)
    private String content;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
