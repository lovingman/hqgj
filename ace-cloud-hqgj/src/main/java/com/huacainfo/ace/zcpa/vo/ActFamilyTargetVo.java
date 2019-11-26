package com.huacainfo.ace.zcpa.vo;

import com.huacainfo.ace.zcpa.model.ActFamilyTarget;
import com.huacainfo.ace.zcpa.model.ActFamilyVolunteer;

import java.util.List;


public class ActFamilyTargetVo extends ActFamilyTarget {
private static final long serialVersionUID = 1L;
 //被服务对象信息
    private String  ppersonName;
    private String psex;
    private String pidCard;
    private String pcateogry;
    private String pphone;
    private String pnowAddr;

  private List<ActFamilyVolunteerVo> actFamilyVolunteer;


    public String getPpersonName() {
        return ppersonName;
    }

    public void setPpersonName(String ppersonName) {
        this.ppersonName = ppersonName;
    }

    public String getPsex() {
        return psex;
    }

    public void setPsex(String psex) {
        this.psex = psex;
    }

    public String getPidCard() {
        return pidCard;
    }

    public void setPidCard(String pidCard) {
        this.pidCard = pidCard;
    }

    public String getPcateogry() {
        return pcateogry;
    }

    public void setPcateogry(String pcateogry) {
        this.pcateogry = pcateogry;
    }

    public String getPphone() {
        return pphone;
    }

    public void setPphone(String pphone) {
        this.pphone = pphone;
    }

    public String getPnowAddr() {
        return pnowAddr;
    }

    public void setPnowAddr(String pnowAddr) {
        this.pnowAddr = pnowAddr;
    }

    public List<ActFamilyVolunteerVo> getActFamilyVolunteer() {
        return actFamilyVolunteer;
    }

    public void setActFamilyVolunteer(List<ActFamilyVolunteerVo> actFamilyVolunteer) {
        this.actFamilyVolunteer = actFamilyVolunteer;
    }

}
