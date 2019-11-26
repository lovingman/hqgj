package com.huacainfo.ace.zcpa.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.huacainfo.ace.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 表名：act_project_volunteer
 * 注释：项目志愿者列表<除情暖家庭外>
 *
 * @author heshuang
 * @date 2019-07-26
 */
@ApiModel(description = "请求/返回参数模型")
public class ActProjectVolunteer extends BaseModel {
    /**
     * 字段名：act_project_volunteer.id
     * 注释：主键
     */
    @ApiModelProperty(value = "唯一标识")
    private String id;

    /**
     * 字段名：act_project_volunteer.areaCode
     * 注释：所属辖区
     */
    @ApiModelProperty(value = "所属辖区")
    private String areaCode;

    /**
     * 字段名：act_project_volunteer.projectId
     * 注释：项目主键
     */
    @ApiModelProperty(value = "项目主键")
    private String projectId;

    /**
     * 字段名：act_project_volunteer.volunteerId
     * 注释：志愿者主键
     */
    @ApiModelProperty(value = "志愿者主键")
    private String volunteerId;

    /**
     * 字段名：act_project_volunteer.remark
     * 注释：备注
     */
    private String remark;

    /**
     * 字段名：act_project_volunteer.status
     * 注释：状态 
     */
    private String status;

    /**
     * 字段名：act_project_volunteer.createDate
     * 注释：创建日期
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date createDate;

    /**
     * 是否已报名 0-未报名 1-已报名
     */
    private String isSignUp;

    /**
     * 被服务对象主键
     */
    private String targetId;

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

    public String getIsSignUp() {
        return isSignUp;
    }

    public void setIsSignUp(String isSignUp) {
        this.isSignUp = isSignUp;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }
}