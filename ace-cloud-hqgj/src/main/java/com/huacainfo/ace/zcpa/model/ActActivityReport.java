package com.huacainfo.ace.zcpa.model;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;

/**
 * 表名：act_activity_report
 * 注释：项目活动论坛举报情况
 *
 * @author heshuang
 * @date 2019-08-06
 */
public class ActActivityReport extends BaseModel {
    /**
     * 字段名：act_activity_report.id
     * 注释：主键
     */
    private String id;

    /**
     * 字段名：act_activity_report.activityId
     * 注释：活动详情主键
     */
    private String activityId;

    /**
     * 字段名：act_activity_report.postingId
     * 注释：论坛发贴记录ID
     */
    private String postingId;

    /**
     * 字段名：act_activity_report.userId
     * 注释：举报人主键
     */
    private String userId;

    /**
     * 字段名：act_activity_report.reportedUserId
     * 注释：被举报人id
     */
    private String reportedUserId;

    /**
     * 字段名：act_activity_report.reportContemt
     * 注释：举报内容
     */
    private String reportContemt;

    /**
     * 字段名：act_activity_report.remark
     * 注释：备注
     */
    private String remark;

    /**
     * 字段名：act_activity_report.status
     * 注释：状态 
     */
    private String status;

    /**
     * 字段名：act_activity_report.createDate
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

    public String getReportedUserId() {
        return reportedUserId;
    }

    public void setReportedUserId(String reportedUserId) {
        this.reportedUserId = reportedUserId == null ? null : reportedUserId.trim();
    }

    public String getReportContemt() {
        return reportContemt;
    }

    public void setReportContemt(String reportContemt) {
        this.reportContemt = reportContemt == null ? null : reportContemt.trim();
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