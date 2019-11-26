package com.huacainfo.ace.zcpa.model;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;

/**
 * 表名：act_topic_volunteer
 * 注释：项目活动主题参与志愿者列表
 *
 * @author admin
 * @date 2019-08-02
 */
public class ActTopicVolunteer extends BaseModel {
    /**
     * 字段名：act_topic_volunteer.id
     * 注释：主键
     */
    private String id;

    /**
     * 字段名：act_topic_volunteer.topicId
     * 注释：活动主题主键
     */
    private String topicid;

    /**
     * 字段名：act_topic_volunteer.volunteerId
     * 注释：志愿者主键
     */
    private String volunteerid;

    /**
     * 字段名：act_topic_volunteer.remark
     * 注释：备注
     */
    private String remark;

    /**
     * 字段名：act_topic_volunteer.status
     * 注释：状态
     */
    private String status;

    /**
     * 字段名：act_topic_volunteer.createDate
     * 注释：创建日期
     */
    private Date createdate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTopicid() {
        return topicid;
    }

    public void setTopicid(String topicid) {
        this.topicid = topicid == null ? null : topicid.trim();
    }

    public String getVolunteerid() {
        return volunteerid;
    }

    public void setVolunteerid(String volunteerid) {
        this.volunteerid = volunteerid == null ? null : volunteerid.trim();
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
