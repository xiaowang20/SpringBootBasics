package com.wg.basics.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 自定义security
 *  注：
 *  简单配置内存认证
 */
//@Configuration
public class MyWebSecurity extends WebSecurityConfigurerAdapter {

    //采用默认的密码编码器
    @Bean
    PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    //采用BCryptPasswordEncoder加密
    //此时内存认证的密码就不是明文的，一般加密的数据在注册时加密存入数据库的
    //数字10是迭代次数
//    @Bean
//    PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder(10);
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password("111111").roles("ADMIN","USER")
                .and()
                .withUser("wg").password("123456").roles("USER");
    }
}
