package com.huacainfo.ace.zcpa.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.huacainfo.ace.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 表名：act_project_activity
 * 注释：项目志愿者活动详情
 *
 * @author admin
 * @date 2019-07-30
 */
@ApiModel(description = "请求/返回参数模型")
public class ActProjectActivity extends BaseModel {
    /**
     * 字段名：act_project_activity.id
     * 注释：主键
     */
    @ApiModelProperty(value = "唯一标识")
    private String id;

    /**
     * 字段名：act_project_activity.areaCode
     * 注释：所属辖区
     */
    @ApiModelProperty(value = "所属辖区")
    private String areaCode;

    /**
     * 字段名：act_project_activity.projectId
     * 注释：项目主键
     */
    @ApiModelProperty(value = "项目主键")
    private String projectId;

    /**
     * 字段名：act_project_activity.projectCategory
     * 注释：项目类型
     */
    @ApiModelProperty(value = "项目类型")
    private String projectCategory;

    /**
     * 字段名：act_project_activity.topicId
     * 注释：活动主题主键
     */
    @ApiModelProperty(value = "活动主题主键")
    private String topicId;

    /**
     * 字段名：act_project_activity.targetId
     * 注释：帮扶对象主键
     */
    @ApiModelProperty(value = "帮扶对象主键")
    private String targetId;

    /**
     * 字段名：act_project_activity.volunteerId
     * 注释：志愿者主键
     */
    @ApiModelProperty(value = "志愿者主键")
    private String volunteerId;

    /**
     * 字段名：act_project_activity.content
     * 注释：服务内容
     */
    @ApiModelProperty(value = "服务内容")
    private String content;

    /**
     * 字段名：act_project_activity.startTime
     * 注释：服务开始时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    @ApiModelProperty(value = "服务开始时间")
    private Date startTime;

    /**
     * 字段名：act_project_activity.startSite
     * 注释：服务开始地点
     */
    @ApiModelProperty(value = "服务开始地点")
    private String startSite;

    /**
     * 字段名：act_project_activity.startLng
     * 注释：服务开始地点经度
     */
    @ApiModelProperty(value = "服务开始地点经度")
    private BigDecimal startLng;

    /**
     * 字段名：act_project_activity.startLat
     * 注释：服务开始地点纬度
     */
    @ApiModelProperty(value = "服务开始地点纬度")
    private BigDecimal startLat;

    /**
     * 字段名：act_project_activity.endTime
     * 注释：服务结束时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(value = "服务结束时间")
    private Date endTime;

    /**
     * 字段名：act_project_activity.endSite
     * 注释：服务结束地点
     */
    @ApiModelProperty(value = "服务结束地点")
    private String endSite;

    /**
     * 字段名：act_project_activity.endLng
     * 注释：服务结束地点经度
     */
    @ApiModelProperty(value = "服务结束地点经度")
    private BigDecimal endLng;

    /**
     * 字段名：act_project_activity.endLat
     * 注释：服务结束地点纬度
     */
    @ApiModelProperty(value = "服务结束地点纬度")
    private BigDecimal endLat;

    /**
     * 字段名：act_project_activity.isYc
     * 注释：是否发现异常 y-是 n-否
     */
    @ApiModelProperty(value = "是否发现异常 y-是 n-否")
    private String isYc;

    /**
     * 字段名：act_project_activity.activityJiFen
     * 注释：活动积分
     */
    @ApiModelProperty(value = "活动积分")
    private Integer activityJiFen;

    /**
     * 字段名：act_project_activity.activityScore
     * 注释：活动评分
     */
    @ApiModelProperty(value = "活动评分")
    private Integer activityScore;

    /**
     * 字段名：act_project_activity.remark
     * 注释：备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 字段名：act_project_activity.status
     * 注释：状态 1-通过 2-未通过
     */
    @ApiModelProperty(value = "状态 1-通过 2-未通过")
    private String status;

    /**
     * 字段名：act_project_activity.createUserId
     * 注释：创建人编号
     */
    @ApiModelProperty(value = "创建人编号")
    private String createUserId;

    /**
     * 字段名：act_project_activity.createUserName
     * 注释：创建人姓名
     */
    @ApiModelProperty(value = "创建人姓名")
    private String createUserName;

    /**
     * 字段名：act_project_activity.createDate
     * 注释：创建日期
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(value = "创建日期")
    private Date createDate;

    /**
     * 字段名：act_project_activity.lastModifyUserId
     * 注释：更新人编号
     */
    @ApiModelProperty(value = "更新人编号")
    private String lastModifyUserId;

    /**
     * 字段名：act_project_activity.lastModifyUserName
     * 注释：更新人名称
     */
    @ApiModelProperty(value = "更新人名称")
    private String lastModifyUserName;

    /**
     * 字段名：act_project_activity.lastModifyDate
     * 注释：更新日期
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(value = "更新日期")
    private Date lastModifyDate;

    /**
     *活动标题
     */
    @ApiModelProperty(value = "活动标题")
    private String activityTitle;

    /**
     * 异常描述
     */
    @ApiModelProperty(value = "异常描述")
    private String  ycDesc;

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

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId == null ? null : topicId.trim();
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

    public String getStartSite() {
        return startSite;
    }

    public void setStartSite(String startSite) {
        this.startSite = startSite == null ? null : startSite.trim();
    }

    public BigDecimal getStartLng() {
        return startLng;
    }

    public void setStartLng(BigDecimal startLng) {
        this.startLng = startLng;
    }

    public BigDecimal getStartLat() {
        return startLat;
    }

    public void setStartLat(BigDecimal startLat) {
        this.startLat = startLat;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getEndSite() {
        return endSite;
    }

    public void setEndSite(String endSite) {
        this.endSite = endSite == null ? null : endSite.trim();
    }

    public BigDecimal getEndLng() {
        return endLng;
    }

    public void setEndLng(BigDecimal endLng) {
        this.endLng = endLng;
    }

    public BigDecimal getEndLat() {
        return endLat;
    }

    public void setEndLat(BigDecimal endLat) {
        this.endLat = endLat;
    }

    public String getIsYc() {
        return isYc;
    }

    public void setIsYc(String isYc) {
        this.isYc = isYc == null ? null : isYc.trim();
    }

    public Integer getActivityJiFen() {
        return activityJiFen;
    }

    public void setActivityJiFen(Integer activityJiFen) {
        this.activityJiFen = activityJiFen;
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

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public String getYcDesc() {
        return ycDesc;
    }

    public void setYcDesc(String ycDesc) {
        this.ycDesc = ycDesc;
    }

    public Integer getActivityScore() {
        return activityScore;
    }

    public void setActivityScore(Integer activityScore) {
        this.activityScore = activityScore;
    }
}
