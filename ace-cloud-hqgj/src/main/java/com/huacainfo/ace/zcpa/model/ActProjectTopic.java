package com.huacainfo.ace.zcpa.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.huacainfo.ace.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 表名：act_project_topic
 * 注释：项目活动主题
 *
 * @author admin
 * @date 2019-07-29
 */
@ApiModel(description = "请求/返回参数模型")
public class ActProjectTopic extends BaseModel {
    /**
     * 字段名：act_project_topic.id
     * 注释：主键
     */
    @ApiModelProperty(value = "唯一标识")
    private String id;

    /**
     * 字段名：act_project_topic.areaCode
     * 注释：所属辖区
     */
    @ApiModelProperty(value = "所属辖区")
    private String areaCode;

    /**
     * 字段名：act_project_topic.projectId
     * 注释：项目主键
     */
    @ApiModelProperty(value = "项目主键")
    private String projectId;

    /**
     * 字段名：act_project_topic.projectCategory
     * 注释：项目类型
     */
    @ApiModelProperty(value = "项目类型")
    private String projectCategory;

    /**
     * 字段名：act_project_topic.targetId
     * 注释：被帮扶对象主键
     */
    @ApiModelProperty(value = "被帮扶对象主键")
    private String targetId;

    /**
     * 字段名：act_project_topic.content
     * 注释：活动主题内容
     */
    @ApiModelProperty(value = "活动主题内容")
    private String content;

    /**
     * 字段名：act_project_topic.startTime
     * 注释：活动主题开始时间
     */
    @ApiModelProperty(value = "活动主题开始时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date startTime;

    /**
     * 字段名：act_project_topic.endTime
     * 注释：活动主题结束时间
     */
    @ApiModelProperty(value = "活动主题结束时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date endTime;

    /**
     * 字段名：act_project_topic.acceptState
     * 注释：志愿者报名开关 0-关 1-开
     */
    @ApiModelProperty(value = "志愿者报名开关 0-关 1-开")
    private String acceptState;

    /**
     * 字段名：act_project_topic.volunteerNum
     * 注释：预计参与人数
     */
    @ApiModelProperty(value = "预计参与人数")
    private Integer volunteerNum;

    /**
     * 字段名：act_project_topic.remark
     * 注释：备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 字段名：act_project_topic.status
     * 注释：状态 1-通过 2-未通过
     */
    @ApiModelProperty(value = "状态 1-成功 2-失败")
    private String status;

    /**
     * 字段名：act_project_topic.createUserId
     * 注释：创建人编号
     */

    private String createUserId;

    /**
     * 字段名：act_project_topic.createUserName
     * 注释：创建人姓名
     */
    private String createUserName;

    /**
     * 字段名：act_project_topic.createDate
     * 注释：创建日期
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createDate;

    /**
     * 字段名：act_project_topic.lastModifyUserId
     * 注释：更新人编号
     */
    private String lastModifyUserId;

    /**
     * 字段名：act_project_topic.lastModifyUserName
     * 注释：更新人名称
     */
    private String lastModifyUserName;

    /**
     * 字段名：act_project_topic.lastModifyDate
     * 注释：更新日期
     */
    private Date lastModifyDate;

    /**
     * 活动标题
     */
    private String title;

    /**
     * 联系人姓名
     */
    private String contactName;

    /**
     * 联系人电话
     */
    private String contactTel;

    /**
     * 当前区域
     */
    private String currentAreaCode;

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

    public String getProjectCategory() {
        return projectCategory;
    }

    public void setProjectCategory(String projectCategory) {
        this.projectCategory = projectCategory == null ? null : projectCategory.trim();
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId == null ? null : targetId.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getAcceptState() {
        return acceptState;
    }

    public void setAcceptState(String acceptState) {
        this.acceptState = acceptState == null ? null : acceptState.trim();
    }

    public Integer getVolunteerNum() {
        return volunteerNum;
    }

    public void setVolunteerNum(Integer volunteerNum) {
        this.volunteerNum = volunteerNum;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public String getCurrentAreaCode() {
        return currentAreaCode;
    }

    public void setCurrentAreaCode(String currentAreaCode) {
        this.currentAreaCode = currentAreaCode;
    }
}
