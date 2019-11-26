package com.huacainfo.ace.common.security.component;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

import java.io.Serializable;

public class CustomGrantedAuthorityImpl implements GrantedAuthority, Serializable {
    private static final long serialVersionUID = 1L;
    private String role;

    public CustomGrantedAuthorityImpl() {

    }

    public CustomGrantedAuthorityImpl(String role) {
        Assert.hasText(role, "A granted authority textual representation is required");
        this.role = role;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof String) {
            return obj.equals(role);
        }

        if (obj instanceof GrantedAuthority) {
            GrantedAuthority attr = (GrantedAuthority) obj;

            return role.equals(attr.getAuthority());
        }

        return false;
    }

    @Override
    public String getAuthority() {
        return role;
    }

    @Override
    public int hashCode() {
        return role.hashCode();
    }

    @Override
    public String toString() {
        return role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
