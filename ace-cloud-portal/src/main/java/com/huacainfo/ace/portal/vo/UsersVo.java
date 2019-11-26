package com.huacainfo.ace.portal.vo;

import com.huacainfo.ace.portal.model.Users;


public class UsersVo extends Users {

    private static final long serialVersionUID = 1L;

    private String corpName;

    private String deptName;

    private String areaName;

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
