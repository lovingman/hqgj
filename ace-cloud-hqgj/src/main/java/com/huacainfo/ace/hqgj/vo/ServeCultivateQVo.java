package com.huacainfo.ace.hqgj.vo;

import com.huacainfo.ace.hqgj.model.ServeCultivate;


public class ServeCultivateQVo extends ServeCultivate {
private static final long serialVersionUID = 1L;

private String[] statuss;
//讲师id
private String lecturerId;
    public String[] getStatuss() {
        return statuss;
    }

    public void setStatuss(String[] statuss) {
        this.statuss = statuss;
    }

    public String getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(String lecturerId) {
        this.lecturerId = lecturerId;
    }
}
