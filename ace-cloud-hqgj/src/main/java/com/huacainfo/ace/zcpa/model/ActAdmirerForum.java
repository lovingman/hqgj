package com.huacainfo.ace.zcpa.model;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;

/**
 * 表名：act_admirer_forum
 * 注释：项目活动回帖点赞情况
 *
 * @author HuaCai008
 * @date 2019-08-13
 */
public class ActAdmirerForum extends BaseModel {
    /**
     * 字段名：act_admirer_forum.id
     * 注释：主键
     */
    private String id;

    /**
     * 字段名：act_admirer_forum.forumId
     * 注释：论坛回帖记录ID
     */
    private String forumid;

    /**
     * 字段名：act_admirer_forum.userId
     * 注释：点赞人主键
     */
    private String userid;

    /**
     * 字段名：act_admirer_forum.admirerState
     * 注释：点赞状态
     */
    private String admirerstate;

    /**
     * 字段名：act_admirer_forum.remark
     * 注释：备注
     */
    private String remark;

    /**
     * 字段名：act_admirer_forum.status
     * 注释：状态
     */
    private String status;

    /**
     * 字段名：act_admirer_forum.createDate
     * 注释：创建日期
     */
    private Date createdate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getForumid() {
        return forumid;
    }

    public void setForumid(String forumid) {
        this.forumid = forumid == null ? null : forumid.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getAdmirerstate() {
        return admirerstate;
    }

    public void setAdmirerstate(String admirerstate) {
        this.admirerstate = admirerstate == null ? null : admirerstate.trim();
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
}
