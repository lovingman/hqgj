package com.huacainfo.ace.portal.model;

import com.huacainfo.ace.common.model.BaseModel;

/**
 * 表名：dept
 * 注释：
 *
 * @author chenxiaoke
 * @date 2019-05-16
 */
public class Dept extends BaseModel {
    /**
     * 字段名：dept.id
     * 注释：编号
     */
    private String id;

    /**
     * 字段名：dept.pid
     * 注释：上级编号
     */
    private String pid;

    /**
     * 字段名：dept.name
     * 注释：科室名称
     */
    private String name;

    /**
     * 字段名：dept.corpId
     * 注释：归属企事业单位
     */
    private String corpId;

    /**
     * 字段名：dept.leader
     * 注释：负责人
     */
    private String leader;

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

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader == null ? null : leader.trim();
    }
}