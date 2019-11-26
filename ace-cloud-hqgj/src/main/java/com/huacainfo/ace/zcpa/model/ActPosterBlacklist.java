package com.huacainfo.ace.zcpa.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;

/**
 * 表名：act_poster_blacklist
 * 注释：发贴人黑名单列表
 *
 * @author heshuang
 * @date 2019-08-07
 */
public class ActPosterBlacklist extends BaseModel {
    /**
     * 字段名：act_poster_blacklist.id
     * 注释：主键
     */
    private String id;

    /**
     * 字段名：act_poster_blacklist.postingId
     * 注释：发贴人主键
     */
    private String postingId;

    /**
     * 字段名：act_poster_blacklist.status
     * 注释：状态 0-失效 1-有效
     */
    private String status;

    /**
     * 字段名：act_poster_blacklist.createUserId
     * 注释：创建人编号
     */
    private String createUserId;

    /**
     * 字段名：act_poster_blacklist.createUserName
     * 注释：创建人姓名
     */
    private String createUserName;

    /**
     * 字段名：act_poster_blacklist.createDate
     * 注释：创建日期
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date createDate;

    /**
     * 字段名：act_poster_blacklist.lastModifyUserId
     * 注释：更新人编号
     */
    private String lastModifyUserId;

    /**
     * 字段名：act_poster_blacklist.lastModifyUserName
     * 注释：更新人名称
     */
    private String lastModifyUserName;

    /**
     * 字段名：act_poster_blacklist.lastModifyDate
     * 注释：更新日期
     */
    private Date lastModifyDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPostingId() {
        return postingId;
    }

    public void setPostingId(String postingId) {
        this.postingId = postingId == null ? null : postingId.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName == null ? null : createUserName.trim();
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
        this.lastModifyUserId = lastModifyUserId == null ? null : lastModifyUserId.trim();
    }

    public String getLastModifyUserName() {
        return lastModifyUserName;
    }

    public void setLastModifyUserName(String lastModifyUserName) {
        this.lastModifyUserName = lastModifyUserName == null ? null : lastModifyUserName.trim();
    }

    public Date getLastModifyDate() {
        return lastModifyDate;
    }

    public void setLastModifyDate(Date lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }
}