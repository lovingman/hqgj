package com.huacainfo.ace.portal.vo;

import com.huacainfo.ace.portal.model.Province;


public class ProvinceVo extends Province {

    private static final long serialVersionUID = 1L;

    private String parentName;

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}
