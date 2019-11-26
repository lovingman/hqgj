package com.huacainfo.ace.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author Zhang Peng
 * @date 2018-12-29
 */
public class ResponseDTO<T> implements java.io.Serializable{

    private Integer status;
    private String message;
    private Date receiveTime;
    private T data;

    public static int FAILED=0;

    public static int SUCCESS=1;

    public ResponseDTO(Integer status, String message) {
        this.status = status;
        this.message = message;
        this.receiveTime = new Date();
    }

    public ResponseDTO() {
        this.status = SUCCESS;
        this.message = "OK";
        this.receiveTime = new Date();
    }

    public ResponseDTO(Integer status, String message, T data) {
        this.status = status;
        this.message = message;
        this.receiveTime = new Date();
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseDTO{" + "status=" + status + ", message='" + message + '\'' + ", receiveTime=" + receiveTime
                + ", data=" + data + '}';
    }
}
