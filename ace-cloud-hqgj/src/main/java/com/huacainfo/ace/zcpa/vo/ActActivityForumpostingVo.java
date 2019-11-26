package com.huacainfo.ace.zcpa.vo;

import com.huacainfo.ace.zcpa.model.ActActivityAttach;
import com.huacainfo.ace.zcpa.model.ActActivityForumposting;

import java.util.List;


public class ActActivityForumpostingVo extends ActActivityForumposting {
    private static final long serialVersionUID = 1L;
    /**
     * 回帖
     */
    private int totalForum;
    /**
     * 举报数
     */
    private int totalReport;
    /**
     * 点赞数
     */
    private int totalAdmirer;

    /**
     * 附件
     */
    private List<ActActivityAttach> actActivityAttach;
    /**
     * 姓名
     */
    private String userName;
    /**
     * 点赞状态 1-已赞;0-未赞
     */
    private int admirerState;
    /**
     * 区域值
     */
    private String areaName;
    /**
     *  是否在黑名单
     */
    private int isBlack;

    /**
     * 头像
     */
    private String headimgurl;
    public int getAdmirerState() {
        return admirerState;
    }

    public void setAdmirerState(int admirerState) {
        this.admirerState = admirerState;
    }


    public int getTotalReport() {
        return totalReport;
    }

    public void setTotalReport(int totalReport) {
        this.totalReport = totalReport;
    }

    public int getTotalForum() {
        return totalForum;
    }

    public void setTotalForum(int totalForum) {
        this.totalForum = totalForum;
    }

    public int getTotalAdmirer() {
        return totalAdmirer;
    }

    public void setTotalAdmirer(int totalAdmirer) {
        this.totalAdmirer = totalAdmirer;
    }

    public List<ActActivityAttach> getActActivityAttach() {
        return actActivityAttach;
    }

    public void setActActivityAttach(List<ActActivityAttach> actActivityAttach) {
        this.actActivityAttach = actActivityAttach;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public int getIsBlack() {
        return isBlack;
    }

    public void setIsBlack(int isBlack) {
        this.isBlack = isBlack;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }
}
