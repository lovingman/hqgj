package com.huacainfo.ace.zcpa.vo;

import com.huacainfo.ace.zcpa.model.ActActivityAttach;
import com.huacainfo.ace.zcpa.model.ActActivityForum;

import java.util.List;


public class ForumVo extends ActActivityForum {
    private String userName; //用户名
    private String fornumId;
    /**
     * 被点赞次数
     */
    private int admirerNum;
    /**
     * 点赞状态 1-已点赞; 0-未点赞
     */
    private int admirerState;
    /**
     * 该条评论是否是自己发表的 1-是; 0-否
     */
    private int selfForumState;

    /**
     * 被举报次数
     */
    private int reportedNum;

    /**
     * 回帖人是否在黑名单
     */
    private int blackStatus;

    /**
     * 附件列表
     */
    private List<ActActivityAttach> attachList;

    /**
     * 微信头像
     */
    private String headimgurl;
    public int getSelfForumState() {
        return selfForumState;
    }

    public void setSelfForumState(int selfForumState) {
        this.selfForumState = selfForumState;
    }

    public List<ActActivityAttach> getAttachList() {
        return attachList;
    }

    public void setAttachList(List<ActActivityAttach> attachList) {
        this.attachList = attachList;
    }

    public int getAdmirerNum() {
        return admirerNum;
    }

    public void setAdmirerNum(int admirerNum) {
        this.admirerNum = admirerNum;
    }

    public int getAdmirerState() {
        return admirerState;
    }

    public void setAdmirerState(int admirerState) {
        this.admirerState = admirerState;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFornumId() {
        return fornumId;
    }

    public void setFornumId(String fornumId) {
        this.fornumId = fornumId;
    }

    public int getReportedNum() {
        return reportedNum;
    }

    public void setReportedNum(int reportedNum) {
        this.reportedNum = reportedNum;
    }

    public int getBlackStatus() {
        return blackStatus;
    }

    public void setBlackStatus(int blackStatus) {
        this.blackStatus = blackStatus;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }
}
