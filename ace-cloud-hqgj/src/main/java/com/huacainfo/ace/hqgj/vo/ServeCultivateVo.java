package com.huacainfo.ace.hqgj.vo;

import com.huacainfo.ace.hqgj.model.ServeCultivate;
import com.huacainfo.ace.hqgj.model.ServeCultivateSchedule;

import java.util.List;


public class ServeCultivateVo extends ServeCultivate {
private static final long serialVersionUID = 1L;
 private List<ServeCultivateSchedule> scheduleList;

    public List<ServeCultivateSchedule> getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(List<ServeCultivateSchedule> scheduleList) {
        this.scheduleList = scheduleList;
    }
}
