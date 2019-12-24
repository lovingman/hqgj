package com.huacainfo.ace.hqgj.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.huacainfo.ace.hqgj.model.ServeCultivateEnroll;

import java.util.Date;


public class ServeCultivateEnrollVo extends ServeCultivateEnroll {
    private static final long serialVersionUID = 1L;
    //姓名
    private String name;
   //公司名称
    private String companyName;

    //培训标题
    private String title;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    //开始时间
    private Date startDate;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    //结束时间
    private Date endDate;
    //培训封面
    private String fmUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getCompanyName() {
        return companyName;
    }

    @Override
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getFmUrl() {
        return fmUrl;
    }

    public void setFmUrl(String fmUrl) {
        this.fmUrl = fmUrl;
    }
}
