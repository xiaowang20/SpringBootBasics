package com.wg.bascis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * OAuth2配置
 */
@Configuration
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 注入授权服务器
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 注入授权服务器
     * @return
     */
    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return super.userDetailsService();
    }

    /**
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.inMemoryAuthentication()
               .withUser("admin")
               .roles("admin")
               .password("$2a$10$MCOExuh5xCAvWhWj38oncOyWYME49MUSIUZ3YGNuZCphlGBNA08Rq")
               .and()
               .withUser("sang")
               .password("$2a$10$MCOExuh5xCAvWhWj38oncOyWYME49MUSIUZ3YGNuZCphlGBNA08Rq")
               .roles("user");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/oauth/**")
                .authorizeRequests()
                .antMatchers("/oauth/**")
                .permitAll()
                .and()
                .csrf().disable();
    }
}
