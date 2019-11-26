package com.huacainfo.ace.portal.model;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;

/**
 * 表名：config
 * 注释：系统配置
 *
 * @author HuaCai008
 * @date 2019-05-17
 */
public class Config extends BaseModel {
    /**
     * 字段名：config.id
     * 注释：
     */
    private String id;

    /**
     * 字段名：config.syid
     * 注释：系统编号
     */
    private String syid;

    /**
     * 字段名：config.config_key
     * 注释： 配置key
     */
    private String configKey;

    /**
     * 字段名：config.dept_id
     * 注释：
     */
    private String deptId;

    /**
     * 字段名：config.category
     * 注释：
     */
    private String category;

    /**
     * 字段名：config.config_name
     * 注释： 名称
     */
    private String configName;

    /**
     * 字段名：config.config_value
     * 注释：数值
     */
    private String configValue;

    /**
     * 字段名：config.remark
     * 注释：
     */
    private String remark;

    /**
     * 字段名：config.create_time
     * 注释：入库时间
     */
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSyid() {
        return syid;
    }

    public void setSyid(String syid) {
        this.syid = syid == null ? null : syid.trim();
    }

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey == null ? null : configKey.trim();
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName == null ? null : configName.trim();
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue == null ? null : configValue.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
