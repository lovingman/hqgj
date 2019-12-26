package com.huacainfo.ace.hqgj.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class IntegralDetailedVo {
    private static final long serialVersionUID = 1L;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createDate;

    private String integral;

    private String remark;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
