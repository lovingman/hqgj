package com.huacainfo.ace.hqgj.model;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;

/**
 * 表名：law_policy
 * 注释：政策服务表
 *
 * @author admin
 * @date 2019-12-10
 */
public class LawPolicy extends BaseModel {
    /**
     * 字段名：law_policy.id
     * 注释：主键ID
     */
    private String id;

    /**
     * 字段名：law_policy.title
     * 注释：标题名称
     */
    private String title;

    /**
     * 字段名：law_policy.source
     * 注释：来源
     */
    private String source;

    /**
     * 字段名：law_policy.content
     * 注释：内容
     */
    private String content;

    /**
     * 字段名：law_policy.createUserId
     * 注释：创建人ID
     */
    private String createUserId;

    /**
     * 字段名：law_policy.createUserName
     * 注释：创建人姓名
     */
    private String createUserName;

    /**
     * 字段名：law_policy.createDate
     * 注释：创建时间
     */
    private Date createDate;

    /**
     * 字段名：law_policy.modifyUserId
     * 注释：更新人ID
     */
    private String modifyUserId;

    /**
     * 字段名：law_policy.modifyUserName
     * 注释：更新人姓名
     */
    private String modifyUserName;

    /**
     * 字段名：law_policy.modifyDate
     * 注释：修改时间
     */
    private Date modifyDate;

    /**
     * 字段名：law_policy.status
     * 注释：状态  1-待发布  2-已发布
     */
    private String status;

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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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

    public String getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(String modifyUserId) {
        this.modifyUserId = modifyUserId == null ? null : modifyUserId.trim();
    }

    public String getModifyUserName() {
        return modifyUserName;
    }

    public void setModifyUserName(String modifyUserName) {
        this.modifyUserName = modifyUserName == null ? null : modifyUserName.trim();
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}
