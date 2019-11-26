package com.huacainfo.ace.portal.vo;

import com.huacainfo.ace.portal.model.Corp;


public class CorpVo extends Corp {
    private static final long serialVersionUID = 1L;
    private String areaName;
    private String typeName;
    private String pname;

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }
}
