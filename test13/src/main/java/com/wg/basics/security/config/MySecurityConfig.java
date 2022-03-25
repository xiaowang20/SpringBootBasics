package com.wg.basics.security.config;

import com.wg.basics.entity.Menu;
import com.wg.basics.mapper.MenuMapper;
import com.wg.basics.security.component.CustomAccessDecisionManager;
import com.wg.basics.security.component.CustomFilterInvocationSecurityMetadataSource;
import com.wg.basics.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import java.util.List;

/**
 * 实现动态权限配置
 */
@Configuration
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    final UserService userService;
    MySecurityConfig(UserService userService){
        this.userService=userService;
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
http.authorizeRequests()
        .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
            @Override
            public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                object.setSecurityMetadataSource(cfisms());
                object.setAccessDecisionManager(cadm());
                return object;
            }
        })
        .and()
        .formLogin()
        .loginProcessingUrl("/login").permitAll()
        .and()
        .cors().disable();
    }


    //注入资源拦截器
    @Bean
    CustomFilterInvocationSecurityMetadataSource cfisms(){
    return new CustomFilterInvocationSecurityMetadataSource();
    }

    //注入访问失效管理器
    @Bean
    CustomAccessDecisionManager cadm(){
        return new CustomAccessDecisionManager();
    }
}
