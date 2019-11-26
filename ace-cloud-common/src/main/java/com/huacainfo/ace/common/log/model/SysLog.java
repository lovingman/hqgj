package com.huacainfo.ace.common.log.model;

import java.util.Date;

public class SysLog implements java.io.Serializable {

    private String id;

    /**
     * 被操作的对象
     */
    private String operationObj;

    /**
     * 操作类型
     */
    private String operationType;

    /**
     * 日志描述
     */
    private String describe;


    /**
     * 操作人id
     */
    private String userId;
    /**
     * 操作人
     */
    private String userName;

    /**
     * 操作人IP地址
     */
    private String operationIp;


    /**
     * 方法运行时间
     */
    private Long runTime;


    private Date createTime;
    /**
     * 方法名
     */
    private String method;
    /**
     * 参数
     */
    private String args;
    /**
     * 方法返回值
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

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
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

    @Override
    public String toString() {
        return "SysLog{" +
                "id='" + id + '\'' +
                ", operationObj='" + operationObj + '\'' +
                ", operationType='" + operationType + '\'' +
                ", describe='" + describe + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", operationIp='" + operationIp + '\'' +
                ", runTime=" + runTime +
                ", createTime=" + createTime +
                ", method='" + method + '\'' +
                ", args='" + args + '\'' +
                ", returnValue='" + returnValue + '\'' +
                '}';
    }
}
