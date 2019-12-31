package com.huacainfo.ace.hqgj.vo;

import com.huacainfo.ace.hqgj.model.BasicAnnex;


public class BasicAnnexQVo extends BasicAnnex {
private static final long serialVersionUID = 1L;
private String[] relationIds;

    public String[] getRelationIds() {
        return relationIds;
    }

    public void setRelationIds(String[] relationIds) {
        this.relationIds = relationIds;
    }
}
