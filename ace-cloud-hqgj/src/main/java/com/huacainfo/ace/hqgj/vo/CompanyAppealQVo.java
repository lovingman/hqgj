package com.huacainfo.ace.hqgj.vo;

import com.huacainfo.ace.hqgj.model.CompanyAppeal;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
public class CompanyAppealQVo extends CompanyAppeal {
private static final long serialVersionUID = 1L;
    /**
     * 开始时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startTime;

    /**
     * 注释：结束时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
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
