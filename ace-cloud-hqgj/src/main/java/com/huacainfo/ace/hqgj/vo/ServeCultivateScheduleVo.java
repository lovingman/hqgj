package com.huacainfo.ace.hqgj.vo;

import com.huacainfo.ace.hqgj.model.BasicAnnex;
import com.huacainfo.ace.hqgj.model.ServeCultivateSchedule;

import java.util.List;


public class ServeCultivateScheduleVo extends ServeCultivateSchedule {
private static final long serialVersionUID = 1L;
    private List<BasicAnnex> basicAnnexes ;

    public List<BasicAnnex> getBasicAnnexes() {
        return basicAnnexes;
    }

    public void setBasicAnnexes(List<BasicAnnex> basicAnnexes) {
        this.basicAnnexes = basicAnnexes;
    }
}