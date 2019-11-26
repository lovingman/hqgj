package com.huacainfo.ace.zcpa.vo;

import com.huacainfo.ace.zcpa.model.ActPerson;
import com.huacainfo.ace.zcpa.model.BaseVolunteer;

import java.util.List;


public class ActPersonVo extends ActPerson {
private static final long serialVersionUID = 1L;

 private List<BaseVolunteer> baseVolunteer;

    public List<BaseVolunteer> getBaseVolunteer() {
        return baseVolunteer;
    }

    public void setBaseVolunteer(List<BaseVolunteer> baseVolunteer) {
        this.baseVolunteer = baseVolunteer;
    }
}
