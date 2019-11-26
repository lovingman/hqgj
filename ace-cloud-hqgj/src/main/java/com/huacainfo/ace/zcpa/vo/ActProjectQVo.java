package com.huacainfo.ace.zcpa.vo;

import com.huacainfo.ace.zcpa.model.ActProject;


public class ActProjectQVo extends ActProject {
private static final long serialVersionUID = 1L;
private  String region;
private  String[] states;
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }


    public String[] getStates() {
        return states;
    }

    public void setStates(String[] states) {
        this.states = states;
    }
}
