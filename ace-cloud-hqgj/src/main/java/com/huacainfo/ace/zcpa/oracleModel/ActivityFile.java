package com.huacainfo.ace.zcpa.oracleModel;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;

public class ActivityFile extends BaseModel {
    private String id;
    //志愿服务id
    private String activityId;
    //附件名称
    private String name;
    //附件路径
    private String filePath;
    //创建时间
    private Date  createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
