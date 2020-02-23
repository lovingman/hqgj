package com.huacainfo.ace.hqgj.vo;

import com.huacainfo.ace.hqgj.model.CompanyAppeal;


public class CompanyAppealExlVo extends CompanyAppeal {
    private static final long serialVersionUID = 1L;
    private String category;

    private Integer number;

    private String categoryName;

    private String typeName;


    public String getCategory() {
        return category;
    }


    public void setCategory(String category) {
        this.category = category;
    }


    public Integer getNumber() {
        return number;
    }


    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
