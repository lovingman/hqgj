package com.huacainfo.ace.common.security.model;

import java.util.Date;

public class Users implements java.io.Serializable {

    /**
     * 字段名：users.user_id
     * 注释：用户ID
     */
    private String userId;

    /**
     * 字段名：users.account
     * 注释：账号
     */
    private String account;

    /**
     * 字段名：users.password
     * 注释：密码
     */
    private String password;

    /**
     * 字段名：users.sex
     * 注释：性别
     */
    private String sex;

    /**
     * 字段名：users.idCard
     * 注释：身份证号
     */
    private String idCard;

    /**
     * 字段名：users.name
     * 注释：姓名
     */
    private String name;

    /**
     * 字段名：users.corpId
     * 注释：企事业单位
     */
    private String corpId;

    /**
     * 字段名：users.deptId
     * 注释：科室
     */
    private String deptId;

    /**
     * 字段名：users.areaCode
     * 注释：行政区划
     */
    private String areaCode;

    /**
     * 字段名：users.birthday
     * 注释：生日
     */
    private Date birthday;

    /**
     * 字段名：users.stauts
     * 注释：状态
     */
    private String status;

    /**
     * 字段名：users.lastLoginTime
     * 注释：最后登录时间
     */
    private Date lastLoginTime;

    /**
     * 字段名：users.mobile
     * 注释：手机号
     */
    private String mobile;

    /**
     * 字段名：users.email
     * 注释：电子邮箱
     */
    private String email;

    /**
     * 字段名：users.createTime
     * 注释：创建时间
     */
    private Date createTime;

    /**
     * 字段名：users.curSyid
     * 注释：当前默认系统编号
     */
    private String curSyid;

    /**
     * 字段名：users.locked
     * 注释：是否锁定
     */
    private String locked;

    /**
     * 字段名：users.lockedTime
     * 注释：锁定时间
     */
    private Date lockedTime;


    private String corpName;

    private String parentCorpId;
    /**
     * 用户类型
     */
    private String userType;

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }


    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCurSyid() {
        return curSyid;
    }

    public void setCurSyid(String curSyid) {
        this.curSyid = curSyid;
    }

    public String getLocked() {
        return locked;
    }

    public void setLocked(String locked) {
        this.locked = locked;
    }

    public Date getLockedTime() {
        return lockedTime;
    }

    public void setLockedTime(Date lockedTime) {
        this.lockedTime = lockedTime;
    }

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    public String getParentCorpId() {
        return parentCorpId;
    }

    public void setParentCorpId(String parentCorpId) {
        this.parentCorpId = parentCorpId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userId='" + userId + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", idCard='" + idCard + '\'' +
                ", name='" + name + '\'' +
                ", corpId='" + corpId + '\'' +
                ", deptId='" + deptId + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", birthday=" + birthday +
                ", status='" + status + '\'' +
                ", lastLoginTime=" + lastLoginTime +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", createTime=" + createTime +
                ", curSyid='" + curSyid + '\'' +
                ", locked='" + locked + '\'' +
                ", lockedTime=" + lockedTime +
                ", corpName='" + corpName + '\'' +
                ", parentCorpId='" + parentCorpId + '\'' +
                '}';
    }

}
