package com.huacainfo.ace.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

@ApiModel(description = "分页数据对象")
public class PageDTO<T> extends ResponseDTO {
    private static final long serialVersionUID = 1L;
    /**
     * 结果列表
     */
    @ApiModelProperty(value = "结果列表")
    private List<T> rows = new ArrayList<T>();
    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页")
    private Integer page;
    /**
     * 总记录数
     */
    @ApiModelProperty(value = "总记录数")
    private Integer total;

    public PageDTO(int status, String message) {
        super(status, message);

    }

    public PageDTO() {
        super();
    }

    public PageDTO(Integer page, Integer total, List<T> rows) {
        super();
        setRows(rows);
        setTotal(total);

    }

    /**
     * 结果列表
     */
    public List<T> getRows() {
        return rows;
    }

    /**
     * 结果列表
     *
     * @param rows
     */
    public void setRows(List<T> rows) {
        this.rows = rows;
    }


    /**
     * 总记录数
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * 总记录数
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

}
