package com.huacainfo.ace.hqgj.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class AnnexVo {
    private  String id;
    private  String type;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createDate;
    private  String fileName;
    private  Integer annexNum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getAnnexNum() {
        return annexNum;
    }

    public void setAnnexNum(Integer annexNum) {
        this.annexNum = annexNum;
    }
}
