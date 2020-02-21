package com.huacainfo.ace.hqgj.vo;

import com.huacainfo.ace.hqgj.model.CompanyAppeal;


public class CompanyAppealVo extends CompanyAppeal {
private static final long serialVersionUID = 1L;
    private String category;

    private Integer number;

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public Integer getNumber() {
        return number;
    }

    @Override
    public void setNumber(Integer number) {
        this.number = number;
    }
}
