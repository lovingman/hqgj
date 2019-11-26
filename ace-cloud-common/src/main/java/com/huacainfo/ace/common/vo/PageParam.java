package com.huacainfo.ace.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;


@ApiModel(value = "分页参数", description = "分页参数对象")
public class PageParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "排序规则，需与后端同事沟通填写", hidden = false)
    public String orderBy;

    @ApiModelProperty(value = "查询起始位置", hidden = false)
    private int start;

    @ApiModelProperty(value = "查询结束位置", hidden = false)
    private int limit;

    @ApiModelProperty(value = "总记录条数（作为返回对象时才有值）", hidden = false)
    private int totalRecord;

    @ApiModelProperty(value = "当前页码（作为返回对象时才有值）", hidden = false)
    private int pageNum;

    @ApiModelProperty(value = "每页显示条目", hidden = false)
    private int pageSize;

    @ApiModelProperty(value = "排序方式", hidden = false, example = "ASC|DESC")
    private String sord;

    public int getStart() {
    	if(pageNum == 0)
    		return 0;
        return pageSize * (pageNum - 1);
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLimit() {
        if(pageSize>0){
            return pageSize;
        }
        if (limit <= 0) {
            limit = 10;
        }
          return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getOrderBy() {
        if (!StringUtils.isBlank(this.orderBy)) {
            return this.orderBy + " " + this.getSord();
        }
        return null;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public String getSord() {
        return sord;
    }

    public void setSord(String sord) {
        this.sord = sord;
    }

    public int getPageNum() {
        if (pageNum <= 0) {
            pageNum = 1;
        }
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

}
