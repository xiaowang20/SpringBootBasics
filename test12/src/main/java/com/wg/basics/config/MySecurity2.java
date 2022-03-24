package com.wg.basics.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 自定义配置security
 *  访问URL需要那种角色
 *  注：
 *  1.需要实例HTTPSecurity，开启--->配置URL有哪些角色--->配置登录页面（默认的登录页面）
 *  2.其中permitAll():表示与登录相关接口都不需要认证就可以访问
 */
//@Configuration
public class MySecurity2 extends WebSecurityConfigurerAdapter {

    @Bean
    PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    //配置内存认证(哪些用户有哪些角色权限)
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("root").password("1").roles("ADMIN","WG")
                .and()
                .withUser("admin").password("2").roles("ADMIN","USER")
                .and()
                .withUser("wg").password("3").roles("USER");
    }

    //HttpSecurity:配置登录者必须要那种角色或者哪几种角色才能够访问URL
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
            .antMatchers("/admin/**")
            .hasRole("ADMIN")
            .antMatchers("/user/**")
            .access("hasAnyRole('ADMIN','USER')")
            .antMatchers("/user2/**")
            .access("hasRole('ADMIN') and hasRole('WG')")
            .anyRequest()
            .authenticated()
            .and()
            .formLogin()
            .loginProcessingUrl("/login")
            .permitAll()
            .and()
            .csrf()
            .disable();
    }
}
