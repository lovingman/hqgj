package com.huacainfo.ace.zcpa.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.huacainfo.ace.common.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 表名：relation_social_volunteer
 * 注释：志愿者与社会组织关联表
 *
 * @author admin
 * @date 2019-09-26
 */
public class RelationSocialVolunteer extends BaseModel {
    /**
     * 字段名：relation_social_volunteer.id
     * 注释：id主键
     */
    private String id;

    /**
     * 字段名：relation_social_volunteer.volunteerId
     * 注释：志愿者ID (关联base_volunteer表id)
     */
    private String volunteerId;

    /**
     * 字段名：relation_social_volunteer.socialOrgId
     * 注释：社会组织ID (关联base_social_org表id)
     */
    private String socialOrgId;

    /**
     * 字段名：relation_social_volunteer.auditStatus
     * 注释：审核状态 1-审核中 2-已通过 3-未通过
     */
    private String auditStatus;

    /**
     * 字段名：relation_social_volunteer.createDate
     * 注释：创建日期
     */
    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    private Date createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVolunteerId() {
        return volunteerId;
    }

    public void setVolunteerId(String volunteerId) {
        this.volunteerId = volunteerId;
    }

    public String getSocialOrgId() {
        return socialOrgId;
    }

    public void setSocialOrgId(String socialOrgId) {
        this.socialOrgId = socialOrgId;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
