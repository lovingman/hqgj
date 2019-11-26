package com.huacainfo.ace.zcpa.vo;

import com.huacainfo.ace.common.model.BaseModel;
import com.huacainfo.ace.zcpa.model.*;

import java.util.List;


public class VolunteeringVo extends BaseModel {
private static final long serialVersionUID = 1L;
    private String projectId;
    private String targetId;
    private String schoolName;
    private String total;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
