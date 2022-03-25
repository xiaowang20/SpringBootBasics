package com.wg.bascis.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * OAuth2配置
 * 注：
 *  1.自定义资源配置服务器
 *  2.继承ResourceServerConfigurerAdapter
 *  3.@EnableResourceServer：开启资源服务
 *
 */
@Configuration
@EnableResourceServer
public class MyResourceConfig extends ResourceServerConfigurerAdapter {


    /**
     * 配置资源id
     * 注：
     *  1.资源id与授权服务器中的资源id一致
     *  2.仅仅基于令牌认证
     * @param resources
     * @throws Exception
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
       resources.resourceId("rid").stateless(true);
    }

    /**
     * HttpSecurity配置
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("admin")
                .antMatchers("/user/**").hasRole("user")
                .anyRequest().authenticated();
    }
}
