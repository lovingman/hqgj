package com.huacainfo.ace.zcpa.vo;

import com.huacainfo.ace.zcpa.model.ActActivityAttach;
import com.huacainfo.ace.zcpa.model.ActActivityForum;
import com.huacainfo.ace.zcpa.model.ActProjectActivity;

import java.util.List;


public class ActActivityForumVo extends ActActivityForum {
private static final long serialVersionUID = 1L;
private String activityId;
private String userName; //用户名
private  int reportedCount;//被举报次数
private ActProjectActivity actProjectActivity;
private List<ActActivityAttach> actActivityAttach;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getReportedCount() {
        return reportedCount;
    }

    public void setReportedCount(int reportedCount) {
        this.reportedCount = reportedCount;
    }

    public ActProjectActivity getActProjectActivity() {
        return actProjectActivity;
    }

    public void setActProjectActivity(ActProjectActivity actProjectActivity) {
        this.actProjectActivity = actProjectActivity;
    }


    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public List<ActActivityAttach> getActActivityAttach() {
        return actActivityAttach;
    }

    public void setActActivityAttach(List<ActActivityAttach> actActivityAttach) {
        this.actActivityAttach = actActivityAttach;
    }
}
