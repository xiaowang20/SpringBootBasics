package com.wg.basics.security.config;

import com.wg.basics.entity.Hr;
import com.wg.basics.entity.Menu;
import com.wg.basics.security.component.JwtAuthenticationTokenFilter;
import com.wg.basics.security.component.RestAuthenticationEntryPoint;
import com.wg.basics.security.component.RestfulAccessDeniedHandler;
import com.wg.basics.security.vo.AdminUserDetails;
import com.wg.basics.service.HrService;
import com.wg.basics.service.Impl.HrServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

/**
 * 实现动态权限配置
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    HrService hrService;
    @Autowired
    RestfulAccessDeniedHandler restfulAccessDeniedHandler;
    @Autowired
    RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService())
        .passwordEncoder(passwordEncoder());
    }
    @Bean
    public UserDetailsService userDetailsService() {
        //获取登录用户信息
        return username -> {
            Hr admin = hrService.getAdminByUsername(username);
            if (admin != null) {
                List<Menu> permissionList = hrService.getPermissionList(admin.getId());
                return new AdminUserDetails(admin,permissionList);
            }
            throw new UsernameNotFoundException("用户名或密码错误");
        };
    }

    /**
     * 在用户名和密码校验前添加的过滤器，如果有jwt的token，会自行根据token信息进行登录。
     * @return
     */
    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter(){
        return new JwtAuthenticationTokenFilter();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    http.csrf()// 由于使用的是JWT，我们这里不需要csrf
        .disable()
        .sessionManagement()// 基于token，所以不需要session
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
            .antMatchers(HttpMethod.GET, // 允许对于网站静态资源的无授权访问
                    "/",
                    "/*.html",
                    "/favicon.ico",
                    "/**/*.html",
                    "/**/*.css",
                    "/**/*.js",
                    "/swagger-resources/**",
                    "/v2/api-docs/**"
            )
            .permitAll()
            .antMatchers("/admin/login", "/admin/register")// 对登录注册要允许匿名访问
            .permitAll()
            .antMatchers(HttpMethod.OPTIONS)//跨域请求会先进行一次options请求
            .permitAll()
            .anyRequest()// 除上面外的所有请求全部需要鉴权认证
            .authenticated();
    // 禁用缓存
    http.headers().cacheControl();
    // 添加JWT filter
    http.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    //添加自定义未授权和未登录结果返回
    http.exceptionHandling()
            .accessDeniedHandler(restfulAccessDeniedHandler)//当用户没有访问权限时的处理器，用于返回JSON格式的处理结果；
            .authenticationEntryPoint(restAuthenticationEntryPoint);//当未登录或token失效时，返回JSON格式的结果
    }


}
