package com.huacainfo.ace.hqgj.vo;

import com.huacainfo.ace.hqgj.model.ServeBusiness;
import com.huacainfo.ace.hqgj.model.ServeBusinessAppend;

import java.util.List;


public class ServeBusinessVo extends ServeBusiness {
private static final long serialVersionUID = 1L;
    private String areaName;
    private ServeBusiness serveBusiness;
 private List<ServeBusinessDetailVo> businessDetailList;
 private List<ServeBusinessAppend> businessAppendList;

    public List<ServeBusinessDetailVo> getBusinessDetailList() {
        return businessDetailList;
    }

    public void setBusinessDetailList(List<ServeBusinessDetailVo> businessDetailList) {
        this.businessDetailList = businessDetailList;
    }

    public List<ServeBusinessAppend> getBusinessAppendList() {
        return businessAppendList;
    }

    public void setBusinessAppendList(List<ServeBusinessAppend> businessAppendList) {
        this.businessAppendList = businessAppendList;
    }

    public ServeBusiness getServeBusiness() {
        return serveBusiness;
    }

    public void setServeBusiness(ServeBusiness serveBusiness) {
        this.serveBusiness = serveBusiness;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}
