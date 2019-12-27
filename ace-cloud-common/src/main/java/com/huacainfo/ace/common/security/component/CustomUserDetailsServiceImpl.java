package com.huacainfo.ace.common.security.component;

import com.huacainfo.ace.common.constant.CommonKeys;
import com.huacainfo.ace.common.security.feign.FeignSecurityService;
import com.huacainfo.ace.common.security.model.Users;
import com.huacainfo.ace.common.security.vo.UserDetailsImpl;
import com.huacainfo.ace.common.tools.StringKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * 该类的主要作用是为Spring Security提供一个经过用户认证后的UserDetails。
 * 该UserDetails包括用户名、密码、是否可用、是否过期等信息。 sparta 11/3/29
 */
@Component
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private FeignSecurityService feignSecurityService;

    /**
     * 用户名/密码与前端提交的用户名/密码进行对比
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        this.logger.info("登录 {}", username);

        //小程序登录识别
        String[] arr = username.split("\\|");
        if (CommonKeys.WX_LOGIN_TAG.equals(arr[0])) {
            //指定用户授权登录信息
            String account = feignSecurityService.selectUsersByWx(arr[1]);
            // 指定用户授权登录信息
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String password = encoder.encode(CommonKeys.WX_LOGIN_PWD);
            return commonLogin(account, password);
        }
        /**
         * 用户名/密码正常校验登录
         */
        return commonLogin(username, "");
    }

    /**
     * 用户名/密码正常校验登录
     *
     * @param username 用户名
     * @return UserDetails
     */
    private UserDetails commonLogin(String username, String password) {
        //Users users = feignSecurityService.selectUsersByAccount(username);
        Users users = null;
        users = feignSecurityService.selectUsersByAccount(username);

        if (!StringUtils.isEmpty(users)) {
            this.logger.info("开始加载角色");
            List<Map<String, String>> roles = this.feignSecurityService.selectRoleListByUserId(users.getUserId());
            List<String> role = new ArrayList<>();
            Collection<GrantedAuthority> auths = new HashSet<>();
            for (Map<String, String> o : roles) {
                GrantedAuthority e = new CustomGrantedAuthorityImpl(o.get("role"));
                auths.add(e);
                role.add(o.get("id"));
            }
            List<String> roleType = feignSecurityService.selectRoleTypeListByUserId(users.getUserId());
            String[] syid = feignSecurityService.selectSyidByUserId(users.getUserId());
            if (StringUtils.isEmpty(syid)) {
                syid = new String[]{"sys"};
            }
            if (StringUtils.isEmpty(users.getCurSyid())) {
                users.setCurSyid(syid[0]);
            }
            Map<String, String> cfg = this.feignSecurityService.selectUserCfgByUserId(users.getUserId());
            Map<String, String> sycfg = this.feignSecurityService.loadConfig(users.getCurSyid());
            cfg.putAll(sycfg);
            this.logger.info("用户 {} 角色 {} 角色类型 {} 系统编号 {}", users.getAccount(), auths, roleType, syid);
            logger.info("users{}", users);
            password = StringKit.isEmpty(password) ? users.getPassword() : password;
            UserDetailsImpl o = new UserDetailsImpl(users.getUserId(), password, users.getAccount(),
                    users.getName(), users.getName(),
                    users.getCorpId(), users.getCorpName(), users.getAreaCode(),
                    users.getStatus().equals("1"), true, true, true,
                    auths, roleType, users.getParentCorpId(), users.getDeptId(), users.getEmail(), users.getAccount(),
                    role, syid, users.getCurSyid(), cfg);

            if (users.getLocked() != null) {
                if (users.getLocked().equals("1")) {
                    o.setAccountNonLocked(false);
                }
            }
            logger.info("{}", o);
            return o;
        } else {
            throw new BadCredentialsException("登陆失败,用户名不存在");
        }
    }
}
