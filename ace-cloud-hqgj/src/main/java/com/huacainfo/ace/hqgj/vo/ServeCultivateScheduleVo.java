package com.huacainfo.ace.hqgj.vo;

import com.huacainfo.ace.hqgj.model.BaseOrganizationMember;
import com.huacainfo.ace.hqgj.model.BasicAnnex;
import com.huacainfo.ace.hqgj.model.ServeCultivateSchedule;

import java.util.List;


public class ServeCultivateScheduleVo extends ServeCultivateSchedule {
private static final long serialVersionUID = 1L;
 private BaseOrganizationMember baseOrganizationMember;

    public BaseOrganizationMember getBaseOrganizationMember() {
        return baseOrganizationMember;
    }

    public void setBaseOrganizationMember(BaseOrganizationMember baseOrganizationMember) {
        this.baseOrganizationMember = baseOrganizationMember;
    }
}
