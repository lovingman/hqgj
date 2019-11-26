package com.huacainfo.ace.common.security.vo;

import com.huacainfo.ace.common.vo.UserProp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class UserDetailsImpl extends UserProp implements UserDetails {

    private static final long serialVersionUID = 1L;
    private String password;
    private String username;
    private boolean accountNonExpired;
    private boolean accountNonLocked;

    private boolean credentialsNonExpired;

    private boolean enabled;
    private Collection<GrantedAuthority> authorities;

    public UserDetailsImpl() {


    }


    public UserDetailsImpl(String userId, String password, String username,
                           String trueName, String nickName, String corpId, String corpName,
                           String areaCode, boolean accountNonExpired,
                           boolean accountNonLocked, boolean credentialsNonExpired,
                           boolean enabled, Collection<GrantedAuthority> authorities,
                           List<String> roleType, String parentCorpId, String deptId, String email,
                           String account, List<String> role, String[] syid, String activeSyId, Map<String, String> cfg) {
        super();
        this.password = password;
        this.username = username;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
        this.authorities = authorities;
        setUserId(userId);
        setCorpId(corpId);
        setName(trueName);
        setCorpId(corpId);
        setCorpName(corpName);
        setAreaCode(areaCode);
        setRoleType(roleType);
        setParentCorpId(parentCorpId);
        setEmail(email);
        setAccount(account);
        setRole(role);
        setSyid(syid);
        setActiveSyId(activeSyId);
        setCfg(cfg);
        setDeptId(deptId);
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return authorities == null ? new HashSet<GrantedAuthority>(0)
                : authorities;
    }

    public void setAuthorities(Collection<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public boolean belongToRole(String role) {
        if (authorities == null) {
            return false;
        }
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.equals(role)) {
                return true;
            }
        }
        return false;
    }
}
