package com.huacainfo.ace.portal.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

public class ResExcelVo extends BaseRowModel implements java.io.Serializable{
    /**
     * 字段名：resources.resources_id
     * 注释：
     */
    @ExcelProperty(value ={"资源编号"},index=0)
    private String resourcesId;

    /**
     * 字段名：resources.parent_resources_id
     * 注释：
     */
    @ExcelProperty(value ={"父编号"},index=1)
    private String parentResourcesId;

    /**
     * 字段名：resources.resources_name
     * 注释：
     */
    @ExcelProperty(value ={"名称"},index=2)
    private String resourcesName;

    /**
     * 字段名：resources.resources_url
     * 注释：
     */
    @ExcelProperty(value ={"地址"},index=3)
    private String resourcesUrl;



    /**
     * 字段名：resources.resources_icon
     * 注释：
     */
    @ExcelProperty(value ={"图标"},index=4)
    private String resourcesIcon;

    /**
     * 字段名：resources.resources_type
     * 注释：
     */
    @ExcelProperty(value ={"类型"},index=5)
    private String resourcesType;


    public String getResourcesId() {
        return resourcesId;
    }

    public void setResourcesId(String resourcesId) {
        this.resourcesId = resourcesId;
    }

    public String getParentResourcesId() {
        return parentResourcesId;
    }

    public void setParentResourcesId(String parentResourcesId) {
        this.parentResourcesId = parentResourcesId;
    }

    public String getResourcesName() {
        return resourcesName;
    }

    public void setResourcesName(String resourcesName) {
        this.resourcesName = resourcesName;
    }

    public String getResourcesUrl() {
        return resourcesUrl;
    }

    public void setResourcesUrl(String resourcesUrl) {
        this.resourcesUrl = resourcesUrl;
    }

    public String getResourcesIcon() {
        return resourcesIcon;
    }

    public void setResourcesIcon(String resourcesIcon) {
        this.resourcesIcon = resourcesIcon;
    }

    public String getResourcesType() {
        return resourcesType;
    }

    public void setResourcesType(String resourcesType) {
        this.resourcesType = resourcesType;
    }
}
