package com.huacainfo.ace.portal.model;

import com.huacainfo.ace.common.model.BaseModel;

/**
 * 表名：crop
 * 注释：
 *
 * @author chenxiaoke
 * @date 2019-05-16
 */
public class Crop extends BaseModel {
    /**
     * 字段名：crop.id
     * 注释：编号
     */
    private String id;

    /**
     * 字段名：crop.pid
     * 注释：上级编号
     */
    private String pid;

    /**
     * 字段名：crop.name
     * 注释：企事业单位名称
     */
    private String name;

    /**
     * 字段名：crop.areaCode
     * 注释：行政区划
     */
    private String areaCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }
}