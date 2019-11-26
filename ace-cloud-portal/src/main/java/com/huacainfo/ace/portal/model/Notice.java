package com.huacainfo.ace.portal.model;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;

/**
 * 表名：notice
 * 注释：公告
 *
 * @author HuaCai008
 * @date 2019-05-17
 */
public class Notice extends BaseModel {
    /**
     * 字段名：notice.notice_id
     * 注释：编号
     */
    private String noticeId;

    /**
     * 字段名：notice.category
     * 注释：类别
     */
    private String category;

    /**
     * 字段名：notice.title
     * 注释：标题
     */
    private String title;

    /**
     * 字段名：notice.publisher
     * 注释：发布者
     */
    private String publisher;

    /**
     * 字段名：notice.top
     * 注释：置顶否
     */
    private String top;

    /**
     * 字段名：notice.status
     * 注释：状态
     */
    private String status;

    /**
     * 字段名：notice.create_time
     * 注释：入库时间
     */
    private Date createTime;

    /**
     * 字段名：notice.publish_time
     * 注释：发布时间
     */
    private Date publishTime;

    /**
     * 字段名：notice.deadline
     * 注释：过期时间
     */
    private Date deadline;

    /**
     * 字段名：notice.department_id
     * 注释：查看机构
     */
    private String departmentId;

    /**
     * 字段名：notice.groups_id
     * 注释：组编号
     */
    private String groupsId;

    /**
     * 字段名：notice.syid
     * 注释：系统编号
     */
    private String syid;

    /**
     * 字段名：notice.content
     * 注释：内容
     */
    private String content;

    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId == null ? null : noticeId.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher == null ? null : publisher.trim();
    }

    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top == null ? null : top.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId == null ? null : departmentId.trim();
    }

    public String getGroupsId() {
        return groupsId;
    }

    public void setGroupsId(String groupsId) {
        this.groupsId = groupsId == null ? null : groupsId.trim();
    }

    public String getSyid() {
        return syid;
    }

    public void setSyid(String syid) {
        this.syid = syid == null ? null : syid.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}
