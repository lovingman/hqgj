package com.huacainfo.ace.common.security.config;

import com.huacainfo.ace.common.security.component.*;
import com.huacainfo.ace.common.security.feign.FeignSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import javax.annotation.Resource;

/**
 * @author chenxiaoke
 * @modify ArvinZou
 */
@Configuration
public class BrowerSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private CustomInvocationSecurityMetadataSource customInvocationSecurityMetadataSource;

    @Autowired
    private CustomAccessDecisionManager customAccessDecisionManager;

    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @Autowired
    private MyAuthenctiationFailureHandler myAuthenctiationFailureHandler;

    @Autowired
    private MyAuthenctiationSuccessHandler myAuthenctiationSuccessHandler;
    @Autowired
    private MyLogoutSuccessHandler myLogoutSuccessHandler;
    @Resource
    private FeignSecurityService feignSecurityService;

    @Resource
    private UserDetailsService userDetailsService;

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }


    @Bean
    LoginAuthenticationProvider loginAuthenticationProvider() {
        return new LoginAuthenticationProvider(userDetailsService);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);

//        //
        auth.authenticationProvider(loginAuthenticationProvider());
//        这东西千万不能忘
        auth.userDetailsService(userDetailsService);//.passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setSecurityMetadataSource(customInvocationSecurityMetadataSource);
                        o.setAccessDecisionManager(customAccessDecisionManager);
                        return o;
                    }
                })
                //配置登录页面
                .and().formLogin()

                // 设置登录页面
                .loginPage("/login")
                // 自定义的登录接口
                .loginProcessingUrl("/security/login")

                // 自定义登录成功处理
                .successHandler(myAuthenctiationSuccessHandler)
                // 自定义登录失败处理
                .failureHandler(myAuthenctiationFailureHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/login", "/security/**", "/css/**",
                        "/js/**", "/img/**", "/www/**", "/portal/www",
                        "/feign/**", "/portal/feign/**"
                ).permitAll()
                .anyRequest().fullyAuthenticated()
                .and()
                .csrf().disable()// 关闭csrf防护
                .exceptionHandling()
                .accessDeniedHandler(customAccessDeniedHandler);
        //支持跨域
        //.and().cors();

        http.logout().logoutUrl("/security/logout").logoutSuccessHandler(myLogoutSuccessHandler);


    }

    /**
     * 在这里配置哪些页面不需要认证
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        //web.ignoring().antMatchers("/login","/security/**","/css/**","/js/**","/img/**","/set/**","/get/**","/feign/**");
    }


    @Bean
    public FilterRegistrationBean filterSecurityRegistration() {
        CustomFilterSecurityInterceptor customFilterSecurityInterceptor = new CustomFilterSecurityInterceptor();
        customFilterSecurityInterceptor.setFeignSecurityService(feignSecurityService);
        customFilterSecurityInterceptor.setCustomInvocationSecurityMetadataSource(this.customInvocationSecurityMetadataSource);
        customFilterSecurityInterceptor.setAccessDecisionManager(this.customAccessDecisionManager);
        FilterRegistrationBean registration = new FilterRegistrationBean(customFilterSecurityInterceptor);
        //
        registration.addUrlPatterns("/*");
        //
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("customFilterSecurityInterceptor");
        return registration;
    }


}
