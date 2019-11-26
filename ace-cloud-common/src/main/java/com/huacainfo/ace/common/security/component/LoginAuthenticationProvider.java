package com.huacainfo.ace.common.security.component;

import com.huacainfo.ace.common.constant.CommonKeys;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

/**
 * @ClassName LoginAuthenticationProvider
 * @Description TODO
 * @Author Arvin Zou
 * @Date 2019/10/12 15:30
 */
public class LoginAuthenticationProvider extends DaoAuthenticationProvider {

    private static final String CUSTOM_REMINDER_UN_MATCH = "用户名和密码不匹配";
    private static final String Message_BadCredentials_default = "Bad credentials";
    private static final String CUSTOM_REMINDER_ACCOUNT_LOCKED = "输入错误次数过多,请30分钟后重试";

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public LoginAuthenticationProvider(UserDetailsService userDetailsService) {
        super();
        // 这个地方一定要对userDetailsService赋值，不然userDetailsService是null (这个坑有点深)
        setUserDetailsService(userDetailsService);
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

//        super.additionalAuthenticationChecks(userDetails, authentication);

        //自定义密码验证方式
        customAuthentication(userDetails, authentication);
    }


    private UserDetailsChecker preAuthenticationChecks = new LoginAuthenticationProvider.DefaultPreAuthenticationChecks();
    private UserDetailsChecker postAuthenticationChecks = new LoginAuthenticationProvider.DefaultPostAuthenticationChecks();

    private void customAuthentication(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) {
        String username = (String) authentication.getPrincipal();
        if (username.startsWith(CommonKeys.UN_VERIFICATION_TAG)) {
            //明文密码对比
            String presentedPassword = authentication.getCredentials().toString();
            if (!presentedPassword.equals(userDetails.getPassword())) {
                this.logger.debug("Authentication failed: password does not match stored value");
                throw new BadCredentialsException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", CUSTOM_REMINDER_UN_MATCH));
            }
            if (authentication.getCredentials() == null) {
                this.logger.debug("Authentication failed: no credentials provided");
                throw new BadCredentialsException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", CUSTOM_REMINDER_UN_MATCH));
            }
        } else {
            //加密密码对比
            if (authentication.getCredentials() == null) {
                this.logger.debug("Authentication failed: no credentials provided");
                throw new BadCredentialsException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", CUSTOM_REMINDER_UN_MATCH));
            } else {
                String presentedPassword = authentication.getCredentials().toString();
                if (!this.passwordEncoder.matches(presentedPassword, userDetails.getPassword())) {
                    this.logger.debug("Authentication failed: password does not match stored value");
                    throw new BadCredentialsException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", CUSTOM_REMINDER_UN_MATCH));
                }
            }
        }
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        return super.authenticate(authentication);

        Assert.isInstanceOf(UsernamePasswordAuthenticationToken.class, authentication, this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.onlySupports", "Only UsernamePasswordAuthenticationToken is supported"));
        String username = authentication.getPrincipal() == null ? "NONE_PROVIDED" : authentication.getName();
        boolean cacheWasUsed = true;
        UserDetails user = getUserCache().getUserFromCache(username);
        if (user == null) {
            cacheWasUsed = false;

            try {
                user = this.retrieveUser(username, (UsernamePasswordAuthenticationToken) authentication);
            } catch (UsernameNotFoundException var6) {
                this.logger.debug("User '" + username + "' not found");
                if (this.hideUserNotFoundExceptions) {
                    throw new BadCredentialsException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
                }

                throw var6;
            }

            Assert.notNull(user, "retrieveUser returned null - a violation of the interface contract");
        }

        try {
            this.preAuthenticationChecks.check(user);
            this.additionalAuthenticationChecks(user, (UsernamePasswordAuthenticationToken) authentication);
        } catch (AuthenticationException var7) {
            if (!cacheWasUsed) {
                throw var7;
            }

            cacheWasUsed = false;
            user = this.retrieveUser(username, (UsernamePasswordAuthenticationToken) authentication);
            this.preAuthenticationChecks.check(user);
            this.additionalAuthenticationChecks(user, (UsernamePasswordAuthenticationToken) authentication);
        }

        this.postAuthenticationChecks.check(user);
        if (!cacheWasUsed) {
            getUserCache().putUserInCache(user);
        }

        Object principalToReturn = user;
        if (isForcePrincipalAsString()) {
            principalToReturn = user.getUsername();
        }

        return this.createSuccessAuthentication(principalToReturn, authentication, user);
    }

    private class DefaultPreAuthenticationChecks implements UserDetailsChecker {
        private DefaultPreAuthenticationChecks() {
        }

        @Override
        public void check(UserDetails user) {
            if (!user.isAccountNonLocked()) {
                LoginAuthenticationProvider.this.logger.debug("User account is locked");
                //"User account is locked"
                throw new LockedException(LoginAuthenticationProvider.this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.locked", CUSTOM_REMINDER_ACCOUNT_LOCKED));
            } else if (!user.isEnabled()) {
                LoginAuthenticationProvider.this.logger.debug("User account is disabled");
                throw new DisabledException(LoginAuthenticationProvider.this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.disabled", "User is disabled"));
            } else if (!user.isAccountNonExpired()) {
                LoginAuthenticationProvider.this.logger.debug("User account is expired");
                throw new AccountExpiredException(LoginAuthenticationProvider.this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.expired", "User account has expired"));
            }
        }
    }

    private class DefaultPostAuthenticationChecks implements UserDetailsChecker {
        private DefaultPostAuthenticationChecks() {
        }

        @Override
        public void check(UserDetails user) {
            if (!user.isCredentialsNonExpired()) {
                LoginAuthenticationProvider.this.logger.debug("User account credentials have expired");
                throw new CredentialsExpiredException(LoginAuthenticationProvider.this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.credentialsExpired", "User credentials have expired"));
            }
        }
    }
}
