package com.huacainfo.ace.portal.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.huacainfo.ace.common.model.BaseModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 表名：users
 * 注释：
 *
 * @author chenxiaoke
 * @date 2019-05-16
 */
public class Users extends BaseModel {
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date lockedTime;

    /**
     * 字段名：users.identity
     * 注释：身份证号，作为关联person表的依据
     */
    private String identity;

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
        this.account = account == null ? null : account.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }



    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
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
        this.curSyid = curSyid == null ? null : curSyid.trim();
    }

    public String getLocked() {
        return locked;
    }

    public void setLocked(String locked) {
        this.locked = locked == null ? null : locked.trim();
    }

    public Date getLockedTime() {
        return lockedTime;
    }

    public void setLockedTime(Date lockedTime) {
        this.lockedTime = lockedTime;
    }

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		 this.identity = identity == null ? null : identity.trim();
	}
}
