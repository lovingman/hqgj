package com.huacainfo.ace.hqgj.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.huacainfo.ace.hqgj.model.ServeCultivateEnroll;

import java.util.Date;


public class ServeCultivateEnrollVo extends ServeCultivateEnroll {
    private static final long serialVersionUID = 1L;
    private String name;

    private String companyName;


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
}
