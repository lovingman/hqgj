package com.huacainfo.ace.portal.model;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;

/**
 * 表名：resources
 * 注释：
 *
 * @author HuaCai008
 * @date 2019-05-17
 */
public class Resources extends BaseModel {
    /**
     * 字段名：resources.resources_id
     * 注释：
     */
    private String resourcesId;

    /**
     * 字段名：resources.parent_resources_id
     * 注释：
     */
    private String parentResourcesId;

    /**
     * 字段名：resources.resources_name
     * 注释：
     */
    private String resourcesName;

    /**
     * 字段名：resources.resources_url
     * 注释：
     */
    private String resourcesUrl;

    /**
     * 字段名：resources.resources_path
     * 注释：
     */
    private String resourcesPath;

    /**
     * 字段名：resources.resources_icon
     * 注释：
     */
    private String resourcesIcon;

    /**
     * 字段名：resources.resources_type
     * 注释：
     */
    private String resourcesType;

    /**
     * 字段名：resources.create_time
     * 注释：
     */
    private Date createTime;

    /**
     * 字段名：resources.create_user_id
     * 注释：
     */
    private String createUserId;

    /**
     * 字段名：resources.remark
     * 注释：
     */
    private String remark;

    /**
     * 字段名：resources.stauts
     * 注释：
     */
    private String stauts;

    /**
     * 字段名：resources.sequence
     * 注释：
     */
    private Integer sequence;

    /**
     * 字段名：resources.syid
     * 注释：系统编号
     */
    private String syid;

    public String getResourcesId() {
        return resourcesId;
    }

    public void setResourcesId(String resourcesId) {
        this.resourcesId = resourcesId == null ? null : resourcesId.trim();
    }

    public String getParentResourcesId() {
        return parentResourcesId;
    }

    public void setParentResourcesId(String parentResourcesId) {
        this.parentResourcesId = parentResourcesId == null ? null : parentResourcesId.trim();
    }

    public String getResourcesName() {
        return resourcesName;
    }

    public void setResourcesName(String resourcesName) {
        this.resourcesName = resourcesName == null ? null : resourcesName.trim();
    }

    public String getResourcesUrl() {
        return resourcesUrl;
    }

    public void setResourcesUrl(String resourcesUrl) {
        this.resourcesUrl = resourcesUrl == null ? null : resourcesUrl.trim();
    }

    public String getResourcesPath() {
        return resourcesPath;
    }

    public void setResourcesPath(String resourcesPath) {
        this.resourcesPath = resourcesPath == null ? null : resourcesPath.trim();
    }

    public String getResourcesIcon() {
        return resourcesIcon;
    }

    public void setResourcesIcon(String resourcesIcon) {
        this.resourcesIcon = resourcesIcon == null ? null : resourcesIcon.trim();
    }

    public String getResourcesType() {
        return resourcesType;
    }

    public void setResourcesType(String resourcesType) {
        this.resourcesType = resourcesType == null ? null : resourcesType.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getStauts() {
        return stauts;
    }

    public void setStauts(String stauts) {
        this.stauts = stauts == null ? null : stauts.trim();
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getSyid() {
        return syid;
    }

    public void setSyid(String syid) {
        this.syid = syid == null ? null : syid.trim();
    }
}
