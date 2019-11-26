package com.huacainfo.ace.zcpa.vo;

import com.huacainfo.ace.zcpa.model.RelationSocialVolunteer;


public class RelationSocialVolunteerVo extends RelationSocialVolunteer {
private static final long serialVersionUID = 1L;
    private  String orgName;
    private  String category;
    private  String name;

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
