package com.wg.basics.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义security配置
 * 注：
 * 登录：
 *  1.自定义登录页面
 *  2.配置访问需要授权的URL时返回到自定义登录页面。
 *  3.自定义用户名和密码
 *  4.登录成功逻辑（登录成功后可以跳转页面，也可以返回JSON字符串）
 *  5.登录失败逻辑（AuthenticationException参数，返回异常。）
 * 登出：
 *  1.清除认证
 *  2.清除session
 *  3.配置登出期间的操作
 *  4.配置登出成功后操作
 */
//@Configuration
public class MySecurity3 extends WebSecurityConfigurerAdapter {
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
                .loginPage("/login_page")
                .loginProcessingUrl("/login")
                .usernameParameter("name")
                .passwordParameter("password")
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication auth) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");//登录成功后返回成功的JSON
                        resp.setStatus(200);//登录成功后返回状态码200
                        Object principal = auth.getPrincipal();//主要的（登录成功后返回用户信息）
                        PrintWriter out = resp.getWriter();//获取输出流
                        Map<String,Object> map = new HashMap<>();
                        map.put("status",200);
                        map.put("message",principal);
                        ObjectMapper objectMapper = new ObjectMapper();
                        String asString = objectMapper.writeValueAsString(map);//将map值序列化成String值
                        out.write(asString);
                        out.flush();//清除流
                        out.close();//关闭流
                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");//登录成功后返回成功的JSON
                        resp.setStatus(401);//登录成功后返回状态码401
                        PrintWriter out = resp.getWriter();//获取输出流
                        Map<String,Object> map = new HashMap<>();
                        map.put("status",401);
                        //定义异常
                        if (e instanceof LockedException){
                            map.put("message","账户被锁定，登录失败！");
                        }else if(e instanceof BadCredentialsException){
                            map.put("message","账户名或密码输入错误，登录失败！");
                        }else if (e instanceof DisabledException){
                            map.put("message","账户被禁用，登录失败！");
                        }else if (e instanceof AccountExpiredException){
                            map.put("message","账户已过期，登录失败！");
                        }else if (e instanceof CredentialsExpiredException){
                            map.put("message","密码已过期，登录失败！");
                        }else{
                            map.put("message","登录失败！");
                        }
                        ObjectMapper objectMapper = new ObjectMapper();
                        String asString = objectMapper.writeValueAsString(map);
                        out.write(asString);
                        out.flush();
                        out.close();
                    }
                })
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .addLogoutHandler(new LogoutHandler() {
                    //登出期间进行的操作（例如：Cookie的清除）
                    @Override
                    public void logout(HttpServletRequest req, HttpServletResponse resp, Authentication auth) {
//                        CookieClearingLogoutHandler clearingLogoutHandler = new CookieClearingLogoutHandler("cookie");
//                        clearingLogoutHandler.logout(req, resp, auth);
                    }
                })
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    //登出成功后的业务逻辑（例如:跳转到登出页面）
                    @Override
                    public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication auth) throws IOException, ServletException {
                            resp.sendRedirect("/logout_page");
                    }
                })
                .and()
                .csrf()
                .disable();
    }
}
