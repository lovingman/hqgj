package com.huacainfo.ace.zcpa.vo;

import com.huacainfo.ace.zcpa.model.*;

import java.util.List;


public class ActProjectVo extends ActProject {
private static final long serialVersionUID = 1L;
    private ActProject actProject;

    private List<ActFamilyTargetVo> actFamilyTarget;

    private ActProjectTopic actProjectTopic;

   private List<ActProjectVolunteerVo> actProjectVolunteer;

   private  List<ActProjectTargetVo> actProjectTarget;

   private String areaName;
    /**
     * 开展活动数
     */
   private int developNum;
    /**
     * 异常数
     */
   private int ycNum;
    /**
     * 综合得分
     */
   private int score;
    /**
     * 参与总时长
     */
   private  int volHour;

    /**
     * 今天是否提交志愿服务
     */
   private int isToday;

    /**
     * 项目还需参与人数
     */
   private int  needNum;

    /**
     * 学校名称
     */
   private String schoolName;

   private String targetId;

    /**
     * 是否有提交志愿服务
     */
   private String isSubmit;
    public ActProject getActProject() {
        return actProject;
    }

    public void setActProject(ActProject actProject) {
        this.actProject = actProject;
    }



    public List<ActFamilyTargetVo> getActFamilyTarget() {
        return actFamilyTarget;
    }

    public void setActFamilyTarget(List<ActFamilyTargetVo> actFamilyTarget) {
        this.actFamilyTarget = actFamilyTarget;
    }

    public void setActProjectTopic(ActProjectTopic actProjectTopic) {
        this.actProjectTopic = actProjectTopic;
    }
    public ActProjectTopic getActProjectTopic() {
        return actProjectTopic;
    }
    public List<ActProjectVolunteerVo> getActProjectVolunteer() {
        return actProjectVolunteer;
    }

    public void setActProjectVolunteer(List<ActProjectVolunteerVo> actProjectVolunteer) {
        this.actProjectVolunteer = actProjectVolunteer;
    }


    public List<ActProjectTargetVo> getActProjectTarget() {
        return actProjectTarget;
    }

    public void setActProjectTarget(List<ActProjectTargetVo> actProjectTarget) {
        this.actProjectTarget = actProjectTarget;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public int getDevelopNum() {
        return developNum;
    }

    public void setDevelopNum(int developNum) {
        this.developNum = developNum;
    }

    public int getYcNum() {
        return ycNum;
    }

    public void setYcNum(int ycNum) {
        this.ycNum = ycNum;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getVolHour() {
        return volHour;
    }

    public void setVolHour(int volHour) {
        this.volHour = volHour;
    }

    public int getIsToday() {
        return isToday;
    }

    public void setIsToday(int isToday) {
        this.isToday = isToday;
    }

    public int getNeedNum() {
        return needNum;
    }

    public void setNeedNum(int needNum) {
        this.needNum = needNum;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getIsSubmit() {
        return isSubmit;
    }

    public void setIsSubmit(String isSubmit) {
        this.isSubmit = isSubmit;
    }
}
