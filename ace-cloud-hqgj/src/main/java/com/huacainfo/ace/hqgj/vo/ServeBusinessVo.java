package com.huacainfo.ace.hqgj.vo;

import com.huacainfo.ace.hqgj.model.ServeBusiness;
import com.huacainfo.ace.hqgj.model.ServeBusinessAppend;
import com.huacainfo.ace.hqgj.model.ServeBusinessDetail;

import java.util.List;


public class ServeBusinessVo extends ServeBusiness {
private static final long serialVersionUID = 1L;
 private List<ServeBusinessDetail> businessDetailList;
 private List<ServeBusinessAppend> businessAppendList;

    public List<ServeBusinessDetail> getBusinessDetailList() {
        return businessDetailList;
    }

    public void setBusinessDetailList(List<ServeBusinessDetail> businessDetailList) {
        this.businessDetailList = businessDetailList;
    }

    public List<ServeBusinessAppend> getBusinessAppendList() {
        return businessAppendList;
    }

    public void setBusinessAppendList(List<ServeBusinessAppend> businessAppendList) {
        this.businessAppendList = businessAppendList;
    }
}
