package com.huacainfo.ace.portal.model;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;

/**
 * 表名：sy_cfg
 * 注释：模块配置表
 *
 * @author HuaCai008
 * @date 2019-05-18
 */
public class SyCfg extends BaseModel {
    /**
     * 字段名：sy_cfg.id
     * 注释：系统编号
     */
    private String id;

    /**
     * 字段名：sy_cfg.name
     * 注释：系统名称
     */
    private String name;

    /**
     * 字段名：sy_cfg.logo
     * 注释：logo
     */
    private String logo;

    /**
     * 字段名：sy_cfg.iconCls
     * 注释：图标样式
     */
    private String iconcls;

    /**
     * 字段名：sy_cfg.colorCls
     * 注释：
     */
    private String colorcls;

    /**
     * 字段名：sy_cfg.createDate
     * 注释：创建日期
     */
    private Date createdate;

    /**
     * 字段名：sy_cfg.createUserId
     * 注释：创建人编号
     */
    private String createuserid;

    /**
     * 字段名：sy_cfg.createUserName
     * 注释：创建人姓名
     */
    private String createusername;

    /**
     * 字段名：sy_cfg.lastModifyDate
     * 注释：最后修改日期
     */
    private Date lastmodifydate;

    /**
     * 字段名：sy_cfg.lastModifyUserId
     * 注释：最后修改人编号
     */
    private String lastmodifyuserid;

    /**
     * 字段名：sy_cfg.lastModifyUserName
     * 注释：最后修改人姓名
     */
    private String lastmodifyusername;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    public String getIconcls() {
        return iconcls;
    }

    public void setIconcls(String iconcls) {
        this.iconcls = iconcls == null ? null : iconcls.trim();
    }

    public String getColorcls() {
        return colorcls;
    }

    public void setColorcls(String colorcls) {
        this.colorcls = colorcls == null ? null : colorcls.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getCreateuserid() {
        return createuserid;
    }

    public void setCreateuserid(String createuserid) {
        this.createuserid = createuserid == null ? null : createuserid.trim();
    }

    public String getCreateusername() {
        return createusername;
    }

    public void setCreateusername(String createusername) {
        this.createusername = createusername == null ? null : createusername.trim();
    }

    public Date getLastmodifydate() {
        return lastmodifydate;
    }

    public void setLastmodifydate(Date lastmodifydate) {
        this.lastmodifydate = lastmodifydate;
    }

    public String getLastmodifyuserid() {
        return lastmodifyuserid;
    }

    public void setLastmodifyuserid(String lastmodifyuserid) {
        this.lastmodifyuserid = lastmodifyuserid == null ? null : lastmodifyuserid.trim();
    }

    public String getLastmodifyusername() {
        return lastmodifyusername;
    }

    public void setLastmodifyusername(String lastmodifyusername) {
        this.lastmodifyusername = lastmodifyusername == null ? null : lastmodifyusername.trim();
    }
}
