package com.huacainfo.ace.portal.vo;

import com.huacainfo.ace.portal.model.Dept;


public class DeptVo extends Dept {
private static final long serialVersionUID = 1L;
private String corpName;

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }
}
