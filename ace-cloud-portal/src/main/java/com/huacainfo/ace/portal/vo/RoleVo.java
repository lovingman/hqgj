package com.huacainfo.ace.portal.vo;

import com.huacainfo.ace.portal.model.Role;


public class RoleVo extends Role {
    private static final long serialVersionUID = 1L;

    private String typeName;
    private String name;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
