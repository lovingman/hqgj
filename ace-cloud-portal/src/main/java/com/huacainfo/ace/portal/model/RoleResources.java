package com.huacainfo.ace.portal.model;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;

/**
 * 表名：role_resources
 * 注释：
 *
 * @author HuaCai008
 * @date 2019-05-18
 */
public class RoleResources extends BaseModel {
    /**
     * 字段名：role_resources.id
     * 注释：
     */
    private Long id;

    /**
     * 字段名：role_resources.role_id
     * 注释：
     */
    private String roleId;

    /**
     * 字段名：role_resources.resources_id
     * 注释：
     */
    private String resourcesId;

    /**
     * 字段名：role_resources.create_time
     * 注释：
     */
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getResourcesId() {
        return resourcesId;
    }

    public void setResourcesId(String resourcesId) {
        this.resourcesId = resourcesId == null ? null : resourcesId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
