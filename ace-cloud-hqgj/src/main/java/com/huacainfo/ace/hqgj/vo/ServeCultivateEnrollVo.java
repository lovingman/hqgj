package com.huacainfo.ace.hqgj.vo;

import com.huacainfo.ace.hqgj.model.ServeCultivateEnroll;

import java.util.Date;


public class ServeCultivateEnrollVo extends ServeCultivateEnroll {
    private static final long serialVersionUID = 1L;
    private String title;
    private Date startDate;
    private Date endDate;
    private String fmUrl;

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
