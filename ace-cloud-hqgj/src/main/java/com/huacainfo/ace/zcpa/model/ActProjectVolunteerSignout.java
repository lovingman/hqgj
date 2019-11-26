package com.huacainfo.ace.zcpa.model;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;

/**
 * 表名：act_project_volunteer_signout
 * 注释：项目志愿者退出列表
 *
 * @author heshuang
 * @date 2019-08-22
 */
public class ActProjectVolunteerSignout extends BaseModel {
    /**
     * 字段名：act_project_volunteer_signout.id
     * 注释：主键
     */
    private String id;

    /**
     * 字段名：act_project_volunteer_signout.areaCode
     * 注释：所属辖区
     */
    private String areaCode;

    /**
     * 字段名：act_project_volunteer_signout.projectId
     * 注释：项目主键
     */
    private String projectId;

    /**
     * 字段名：act_project_volunteer_signout.targetId
     * 注释：被服务对象主键
     */
    private String targetId;

    /**
     * 字段名：act_project_volunteer_signout.volunteerId
     * 注释：志愿者主键
     */
    private String volunteerId;

    /**
     * 字段名：act_project_volunteer_signout.remark
     * 注释：备注
     */
    private String remark;

    /**
     * 字段名：act_project_volunteer_signout.status
     * 注释：状态 
     */
    private String status;

    /**
     * 字段名：act_project_volunteer_signout.createDate
     * 注释：创建日期
     */
    private Date createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId == null ? null : projectId.trim();
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId == null ? null : targetId.trim();
    }

    public String getVolunteerId() {
        return volunteerId;
    }

    public void setVolunteerId(String volunteerId) {
        this.volunteerId = volunteerId == null ? null : volunteerId.trim();
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