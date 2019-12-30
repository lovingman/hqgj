package com.huacainfo.ace.hqgj.vo;

import com.huacainfo.ace.hqgj.model.ServeFinanceOrder;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


public class ServeFinanceOrderQVo extends ServeFinanceOrder {
    private static final long serialVersionUID = 1L;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startTime;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endTime;

    private String[] types;

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

    public String[] getTypes() {
        return types;
    }

    public void setTypes(String[] types) {
        this.types = types;
    }
}
