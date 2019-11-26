package com.huacainfo.ace.zcpa.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;

/**
 * 表名：act_activity_forumposting
 * 注释：论坛发贴记录
 *
 * @author heshuang
 * @date 2019-08-06
 */
public class ActActivityForumposting extends BaseModel {
    /**
     * 字段名：act_activity_forumposting.id
     * 注释：主键
     */
    private String id;

    /**
     * 区域
     */
    private String areaCode;
    /**
     * 字段名：act_activity_forumposting.projectCode
     * 注释：项目类型编号
     */
    private String projectCode;

    /**
     * 字段名：act_activity_forumposting.postingId
     * 注释：发贴人主键
     */
    private String postingId;

    /**
     * 字段名：act_activity_forumposting.title
     * 注释：标题
     */
    private String title;

    /**
     * 字段名：act_activity_forumposting.content
     * 注释：发贴内容
     */
    private String content;

    /**
     * 字段名：act_activity_forumposting.status
     * 注释：状态 0-未通过 1-通过
     */
    private String status;

    /**
     * 字段名：act_activity_forumposting.createUserId
     * 注释：创建人编号
     */
    private String createUserId;

    /**
     * 字段名：act_activity_forumposting.createUserName
     * 注释：创建人姓名
     */
    private String createUserName;

    /**
     * 字段名：act_activity_forumposting.createDate
     * 注释：创建日期(也是发贴时间)
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createDate;

    /**
     * 字段名：act_activity_forumposting.lastModifyUserId
     * 注释：更新人编号
     */
    private String lastModifyUserId;

    /**
     * 字段名：act_activity_forumposting.lastModifyUserName
     * 注释：更新人名称
     */
    private String lastModifyUserName;

    /**
     * 字段名：act_activity_forumposting.lastModifyDate
     * 注释：更新日期
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date lastModifyDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getPostingId() {
        return postingId;
    }

    public void setPostingId(String postingId) {
        this.postingId = postingId;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getLastModifyUserId() {
        return lastModifyUserId;
    }

    public void setLastModifyUserId(String lastModifyUserId) {
        this.lastModifyUserId = lastModifyUserId;
    }

    public String getLastModifyUserName() {
        return lastModifyUserName;
    }

    public void setLastModifyUserName(String lastModifyUserName) {
        this.lastModifyUserName = lastModifyUserName;
    }

    public Date getLastModifyDate() {
        return lastModifyDate;
    }

    public void setLastModifyDate(Date lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }
}