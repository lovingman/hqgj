package com.huacainfo.ace.zcpa.model;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;

/**
 * 表名：act_activity_admirer
 * 注释：项目活动论坛点赞情况
 *
 * @author heshuang
 * @date 2019-08-06
 */
public class ActActivityAdmirer extends BaseModel {
    /**
     * 字段名：act_activity_admirer.id
     * 注释：主键
     */
    private String id;

    /**
     * 字段名：act_activity_admirer.activityId
     * 注释：活动详情主键
     */
    private String activityId;

    /**
     * 字段名：act_activity_admirer.postingId
     * 注释：论坛发贴记录ID
     */
    private String postingId;

    /**
     * 字段名：act_activity_admirer.userId
     * 注释：点赞人主键
     */
    private String userId;

    /**
     * 字段名：act_activity_admirer.admirerState
     * 注释：点赞状态
     */
    private String admirerState;

    /**
     * 字段名：act_activity_admirer.remark
     * 注释：备注
     */
    private String remark;

    /**
     * 字段名：act_activity_admirer.status
     * 注释：状态 
     */
    private String status;

    /**
     * 字段名：act_activity_admirer.createDate
     * 注释：创建日期
     */
    private Date createDate;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId == null ? null : activityId.trim();
    }

    public String getPostingId() {
        return postingId;
    }

    public void setPostingId(String postingId) {
        this.postingId = postingId == null ? null : postingId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getAdmirerState() {
        return admirerState;
    }

    public void setAdmirerState(String admirerState) {
        this.admirerState = admirerState == null ? null : admirerState.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
