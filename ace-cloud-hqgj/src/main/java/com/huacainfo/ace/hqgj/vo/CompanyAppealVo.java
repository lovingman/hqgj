package com.huacainfo.ace.hqgj.vo;

import com.huacainfo.ace.hqgj.model.CompanyAppeal;
import com.huacainfo.ace.hqgj.model.CompanyMaterial;

import java.util.List;


public class CompanyAppealVo extends CompanyAppeal {
private static final long serialVersionUID = 1L;

    private String typeName;

    private List<CompanyMaterial> companyMaterial ;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public List<CompanyMaterial> getCompanyMaterial() {
        return companyMaterial;
    }

    @Override
    public void setCompanyMaterial(List<CompanyMaterial> companyMaterial) {
        this.companyMaterial = companyMaterial;
    }
}
