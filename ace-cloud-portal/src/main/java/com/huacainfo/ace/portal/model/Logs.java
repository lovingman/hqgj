package com.huacainfo.ace.portal.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.huacainfo.ace.common.model.BaseModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 表名：logs
 * 注释：
 *
 * @author HuaCai003
 * @date 2019-06-01
 */
public class Logs extends BaseModel {
    /**
     * 字段名：logs.id
     * 注释：
     */
    private String id;

    /**
     * 字段名：logs.operationObj
     * 注释：
     */
    private String operationObj;

    /**
     * 字段名：logs.operationType
     * 注释：
     */
    private String operationType;

    /**
     * 字段名：logs.userId
     * 注释：
     */
    private String userId;

    /**
     * 字段名：logs.userName
     * 注释：
     */
    private String userName;

    /**
     * 字段名：logs.operationIp
     * 注释：
     */
    private String operationIp;

    /**
     * 字段名：logs.runTime
     * 注释：
     */
    private Long runTime;

    /**
     * 字段名：logs.createTime
     * 注释：
     */
    private Date createTime;

    /**
     * 字段名：logs.describ
     * 注释：
     */
    private String describ;

    /**
     * 字段名：logs.method
     * 注释：
     */
    private String method;

    /**
     * 字段名：logs.args
     * 注释：
     */
    private String args;

    /**
     * 字段名：logs.returnValue
     * 注释：
     */
    private String returnValue;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOperationObj() {
        return operationObj;
    }

    public void setOperationObj(String operationObj) {
        this.operationObj = operationObj;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOperationIp() {
        return operationIp;
    }

    public void setOperationIp(String operationIp) {
        this.operationIp = operationIp;
    }

    public Long getRunTime() {
        return runTime;
    }

    public void setRunTime(Long runTime) {
        this.runTime = runTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDescrib() {
        return describ;
    }

    public void setDescrib(String describ) {
        this.describ = describ;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args;
    }

    public String getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(String returnValue) {
        this.returnValue = returnValue;
    }
}