package com.huacainfo.ace.zcpa.vo;

import com.huacainfo.ace.zcpa.model.BaseSocialOrg;


public class BaseSocialOrgQVo extends BaseSocialOrg {
private static final long serialVersionUID = 1L;

    private  String userId;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
