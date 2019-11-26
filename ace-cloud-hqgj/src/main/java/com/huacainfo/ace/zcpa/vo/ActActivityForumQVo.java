package com.huacainfo.ace.zcpa.vo;

import com.huacainfo.ace.zcpa.model.ActActivityForum;


public class ActActivityForumQVo extends ActActivityForum {
private static final long serialVersionUID = 1L;
private String activityTitle;

private String category;

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
