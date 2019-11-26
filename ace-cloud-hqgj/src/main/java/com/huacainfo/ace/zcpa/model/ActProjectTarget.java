package com.huacainfo.ace.zcpa.model;

import com.huacainfo.ace.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 表名：act_project_target
 * 注释：项目被帮扶对象列表<除情暖家庭外>
 *
 * @author heshuang
 * @date 2019-07-26
 */
@ApiModel(description = "请求/返回参数模型")
public class ActProjectTarget extends BaseModel {
    /**
     * 字段名：act_project_target.id
     * 注释：主键
     */
    @ApiModelProperty(value = "唯一标识")
    private String id;

    /**
     * 字段名：act_project_target.areaCode
     * 注释：所属辖区
     */
    @ApiModelProperty(value = "所属辖区")
    private String areaCode;

    /**
     * 字段名：act_project_target.projectId
     * 注释：项目主键
     */
    @ApiModelProperty(value = "项目主键")
    private String projectId;

    /**
     * 字段名：act_project_target.targetId
     * 注释：帮扶对象主键
     */
    @ApiModelProperty(value = "对象主键")
    private String targetId;

    /**
     * 字段名：act_project_target.targetType
     * 注释：帮扶对象类型
     */
    @ApiModelProperty(value = "唯一标识")
    private String targetType;

    /**
     * 字段名：act_project_target.remark
     * 注释：备注
     */
    private String remark;

    /**
     * 字段名：act_project_target.status
     * 注释：状态 
     */
    private String status;

    /**
     * 字段名：act_project_target.createDate
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

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType == null ? null : targetType.trim();
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