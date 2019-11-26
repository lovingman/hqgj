package com.huacainfo.ace.zcpa.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;

/**
 * 表名：act_activity_forum
 * 注释：项目活动论坛回帖情况
 *
 * @author admin
 * @date 2019-08-02
 */
public class ActActivityForum extends BaseModel {
    /**
     * 字段名：act_activity_forum.id
     * 注释：主键
     */
    private String id;

    /**
     * 字段名：act_activity_forum.activityId
     * 注释：活动详情主键
     */
    private String activityid;

    /**
     * 字段名：act_activity_forum.userId
     * 注释：回帖人主键
     */
    private String userid;

    /**
     * 字段名：act_activity_forum.commentText
     * 注释：回帖内容
     */
    private String commenttext;

    /**
     * 字段名：act_activity_forum.commentTime
     * 注释：回帖时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss ",timezone = "GMT+8")
    private Date commenttime;

    /**
     * 字段名：act_activity_forum.remark
     * 注释：备注
     */
    private String remark;

    /**
     * 字段名：act_activity_forum.status
     * 注释：状态
     */
    private String status;

    /**
     * 字段名：act_activity_forum.createDate
     * 注释：创建日期
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createdate;

    private String postingId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getActivityid() {
        return activityid;
    }

    public void setActivityid(String activityid) {
        this.activityid = activityid == null ? null : activityid.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getCommenttext() {
        return commenttext;
    }

    public void setCommenttext(String commenttext) {
        this.commenttext = commenttext == null ? null : commenttext.trim();
    }

    public Date getCommenttime() {
        return commenttime;
    }

    public void setCommenttime(Date commenttime) {
        this.commenttime = commenttime;
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

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getPostingId() {
        return postingId;
    }

    public void setPostingId(String postingId) {
        this.postingId = postingId;
    }
}
