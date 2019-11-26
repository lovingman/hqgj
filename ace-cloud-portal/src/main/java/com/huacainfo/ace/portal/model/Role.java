package com.huacainfo.ace.portal.model;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;

/**
 * 表名：role
 * 注释：
 *
 * @author HuaCai008
 * @date 2019-05-18
 */
public class Role extends BaseModel {
    /**
     * 字段名：role.role_id
     * 注释：
     */
    private String roleId;

    /**
     * 字段名：role.role_name
     * 注释：
     */
    private String roleName;

    /**
     * 字段名：role.create_time
     * 注释：
     */
    private Date createTime;

    /**
     * 字段名：role.create_user_id
     * 注释：
     */
    private String createUserId;

    /**
     * 字段名：role.remark
     * 注释：
     */
    private String remark;

    /**
     * 字段名：role.type
     * 注释：
     */
    private String type;

    /**
     * 字段名：role.syid
     * 注释：系统编号
     */
    private String syid;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getSyid() {
        return syid;
    }

    public void setSyid(String syid) {
        this.syid = syid == null ? null : syid.trim();
    }
}
