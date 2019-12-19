package com.huacainfo.ace.hqgj.vo;

import com.huacainfo.ace.hqgj.model.ServeFinanceOrder;

import java.util.Date;


public class ServeFinanceOrderQVo extends ServeFinanceOrder {
    private static final long serialVersionUID = 1L;
    private Date startTime;
    private Date endTime;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
