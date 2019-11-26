package com.huacainfo.ace.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

@ApiModel(description = "List集合数据对象")
public class ListDTO<T> extends ResponseDTO {

    private static final long serialVersionUID = 1L;
    /**
     * 其他结果
     */
    @ApiModelProperty(value = "List集合数据列表")
    private List<T> data = new ArrayList<T>();


    public ListDTO(int status, String message) {
        super(status, message);
    }

    public ListDTO() {
        super();
    }

    @Override
    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
