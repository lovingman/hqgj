package com.huacainfo.ace.hqgj.vo;

import com.huacainfo.ace.hqgj.model.CompanyAppeal;
import com.huacainfo.ace.hqgj.model.CompanyMaterial;


public class CompanyAppealExlVo extends CompanyAppeal {
    private String category;

    private Integer number;

    private String categoryName;

    private String typeName;

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
