package com.huacainfo.ace.common.vo;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class UserProp implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 用户标识
     */
    private String userId;
    /**
     * 账号
     */
    private String account;

    /**
     * 用户名称
     */
    private String name;

    /**
     * 所属企业标识
     */
    private String corpId;


    /**
     * 字段名：users.deptId
     * 注释：科室
     */
    private String deptId;
    /**
     * 企业名称
     */
    private String corpName;
    /**
     * 所属地区编码
     */
    private String areaCode;
    /**
     * 上级机构编码
     */
    private String parentCorpId;

    private String ip;

    /**
     * 性别(0：未知；1：男；2：女)
     */
    private String sex;

    private String email;


    private List<String> roleType;
    private List<String> role;

    private String[] syid;

    private String activeSyId;

    private Map<String, String> cfg;



    public UserProp() {

    }

    public UserProp(String userId, String name, String corpId,
                    String corpName, String areaCode, List<String> roleType,
                    String parentCorpId,String deptId, List<String> role, String email, String account, String[] syid, String activeSyId, Map<String, String> cfg) {
        this.userId = userId;
        this.name = name;
        this.corpId = corpId;
        this.deptId=deptId;
        this.corpName = corpName;
        this.areaCode = areaCode;
        this.email = email;
        this.account = account;
        this.parentCorpId = parentCorpId;
        this.role = role;
        this.syid = syid;
        this.roleType = roleType;
        this.activeSyId = activeSyId;
        this.cfg = cfg;
    }

    public UserProp(String userId, String name, String nickName, String corpId,
                    String corpName, String areaCode, String ip, List<String> roleType,
                    String parentCorpId,String deptId, List<String> role, String email, String account, String[] syid, String activeSyId, Map<String, String> cfg) {
        this(userId, name, corpId, corpName, areaCode, roleType,
                parentCorpId, deptId,role, email, account, syid, activeSyId, cfg);
        this.ip = ip;
    }

    /**
     * 用户名称
     */
    public String getName() {
        return name;
    }

    /**
     * 用户名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 所属企业标识
     */
    public String getCorpId() {
        return corpId;
    }

    /**
     * 所属企业标识
     */
    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    /**
     * 企业名称
     */
    public String getCorpName() {
        return corpName;
    }

    /**
     * 企业名称
     */
    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }


    /**
     * 用户标识
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 用户标识
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String toString() {
        return JSON.toJSONString(this);
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 所属地区编码
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * 所属地区编码
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    /**
     * 性别(0：未知；1：男；2：女)
     */
    public String getSex() {
        return sex;
    }

    /**
     * 性别(0：未知；1：男；2：女)
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    public List<String> getRoleType() {
        return roleType;
    }

    public void setRoleType(List<String> roleType) {
        this.roleType = roleType;
    }

    public String getParentCorpId() {
        return parentCorpId;
    }

    public void setParentCorpId(String parentCorpId) {
        this.parentCorpId = parentCorpId;
    }

    public List<String> getRole() {
        return role;
    }

    public void setRole(List<String> role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String[] getSyid() {
        return syid;
    }

    public void setSyid(String[] syid) {
        this.syid = syid;
    }

    public String getActiveSyId() {
        return activeSyId;
    }

    public void setActiveSyId(String activeSyId) {
        this.activeSyId = activeSyId;
    }

    public Map<String, String> getCfg() {
        return cfg;
    }

    public void setCfg(Map<String, String> cfg) {
        this.cfg = cfg;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

}
