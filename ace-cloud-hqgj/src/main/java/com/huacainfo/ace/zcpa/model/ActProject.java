package com.huacainfo.ace.zcpa.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.huacainfo.ace.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 表名：act_project
 * 注释：项目活动列表
 * @author heshuang
 * @date 2019-07-26
 */
@ApiModel(description = "请求/返回参数模型")
public class ActProject extends BaseModel {
    /**
     * 字段名：act_project.id
     * 注释：主键
     */
    @ApiModelProperty(value = "唯一标识")
    private String id;

    /**
     * 字段名：act_project.projectCode
     * 注释：项目编号
     */
    @ApiModelProperty(value = "项目编号")
    private String projectCode;

    /**
     * 字段名：act_project.projectName
     * 注释：项目名称
     */
    @ApiModelProperty(value = "项目名称")
    private String projectName;

    /**
     * 所属主项目ID
     */
    private String pid;

    /**
     * 字段名：act_project.projectLevel
     * 注释：项目级别
     */
    @ApiModelProperty(value = "项目级别")
    private String projectLevel;

    /**
     * 字段名：act_project.category
     * 注释：项目类型
     */
    @ApiModelProperty(value = "项目类型")
    private String category;

    /**
     * 字段名：act_project.areaCode
     * 注释：行政区划
     */
    @ApiModelProperty(value = "行政区划")
    private String areaCode;

    /**
     * 字段名：act_project.startTime
     * 注释：开始时间
     */

    @ApiModelProperty(value = "开始时间")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date startTime;

    /**
     * 字段名：act_project.endTime
     * 注释：结束时间
     */

    @ApiModelProperty(value = "结束时间")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date endTime;

    /**
     * 字段名：act_project.summary
     * 注释：项目介绍
     */
    @ApiModelProperty(value = "项目介绍")
    private String summary;

    /**
     * 字段名：act_project.projectState
     * 注释：项目状态 1-待审核 2-进行中 3-未通过 4-已结束
     */
    @ApiModelProperty(value = "项目状态 1-待审核 2-进行中 3-未通过 4-已结束")
    private String projectState;

    /**
     * 字段名：act_project.actTimes
     * 注释：开展活动次数
     */
    @ApiModelProperty(value = "开展活动次数")
    private Integer actTimes;

    /**
     * 字段名：act_project.overallScore
     * 注释：综合得分
     */
    @ApiModelProperty(value = "综合得分")
    private BigDecimal overallScore;

    /**
     * 字段名：act_project.partakeDuration
     * 注释：参与总时长
     */
    @ApiModelProperty(value = "参与总时长")
    private BigDecimal partakeDuration;

    /**
     * 字段名：act_project.anomalyTimes
     * 注释：发现异常数
     */
    @ApiModelProperty(value = "发现异常数")
    private Integer anomalyTimes;

    /**
     * 字段名：act_project.fmUrl
     * 注释：封面地址
     */
    @ApiModelProperty(value = "封面地址")
    private String fmUrl;

    /**
     * 字段名：act_project.nineteenJiFen
     * 注释：19活动积分 每月17号至21号为19活动
     */
    @ApiModelProperty(value = "19活动积分每月17号至21号为19活动")
    private Integer nineteenJiFen;

    /**
     * 字段名：act_project.jiFen
     * 注释：自定义活动积分
     */
    @ApiModelProperty(value = "自定义活动积分")
    private Integer jiFen;

    /**
     * 字段名：act_project.peopleNumber
     * 注释：需要对接志愿者人数
     */
    @ApiModelProperty(value = "需要对接志愿者人数")
    private Integer peopleNumber;
    /**
     * 字段名：act_project.remark
     * 注释：备注
     */
    private String remark;

    /**
     * 字段名：act_project.status
     * 注释：状态 1-有效 0-失效
     */
    private String status;

    /**
     * 字段名：act_project.createUserId
     * 注释：创建人编号
     */
    private String createUserId;

    /**
     * 字段名：act_project.createUserName
     * 注释：创建人姓名
     */
    private String createUserName;

    /**
     * 字段名：act_project.createDate
     * 注释：创建日期
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss" )
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date createDate;

    /**
     * 字段名：act_project.lastModifyUserId
     * 注释：更新人编号
     */
    private String lastModifyUserId;

    /**
     * 字段名：act_project.lastModifyUserName
     * 注释：更新人名称
     */
    private String lastModifyUserName;

    /**
     * 字段名：act_project.lastModifyDate
     * 注释：更新日期
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date lastModifyDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode == null ? null : projectCode.trim();
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public String getProjectLevel() {
        return projectLevel;
    }

    public void setProjectLevel(String projectLevel) {
        this.projectLevel = projectLevel == null ? null : projectLevel.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    public String getProjectState() {
        return projectState;
    }

    public void setProjectState(String projectState) {
        this.projectState = projectState == null ? null : projectState.trim();
    }

    public Integer getActTimes() {
        return actTimes;
    }

    public void setActTimes(Integer actTimes) {
        this.actTimes = actTimes;
    }

    public BigDecimal getOverallScore() {
        return overallScore;
    }

    public void setOverallScore(BigDecimal overallScore) {
        this.overallScore = overallScore;
    }

    public BigDecimal getPartakeDuration() {
        return partakeDuration;
    }

    public void setPartakeDuration(BigDecimal partakeDuration) {
        this.partakeDuration = partakeDuration;
    }

    public Integer getAnomalyTimes() {
        return anomalyTimes;
    }

    public void setAnomalyTimes(Integer anomalyTimes) {
        this.anomalyTimes = anomalyTimes;
    }

    public String getFmUrl() {
        return fmUrl;
    }

    public void setFmUrl(String fmUrl) {
        this.fmUrl = fmUrl == null ? null : fmUrl.trim();
    }

    public Integer getNineteenJiFen() {
        return nineteenJiFen;
    }

    public void setNineteenJiFen(Integer nineteenJiFen) {
        this.nineteenJiFen = nineteenJiFen;
    }

    public Integer getJiFen() {
        return jiFen;
    }

    public void setJiFen(Integer jiFen) {
        this.jiFen = jiFen;
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

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Integer getPeopleNumber() {
        return peopleNumber;
    }

    public void setPeopleNumber(Integer peopleNumber) {
        this.peopleNumber = peopleNumber;
    }
}