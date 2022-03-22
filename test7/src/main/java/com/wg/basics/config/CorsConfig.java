package com.wg.basics.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 使用全局CorsOrigin方式解决跨域问题。
 * 注：
 *  1.addMapping：添加映射（路径）
 *  2.allowedHeaders：允许请求头信息
 *  3.allowedMethods：允许请求方法
 *  4.allowedOrigins：允许域（协议 + 域名（ip） + 端口号 ）
 *  5.maxAge：探测请求的过期时间（过期之后再发送请求）
 */
//@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/test/**")
            .allowedHeaders("*")
            .allowedMethods("*")
            .allowedOrigins("http://localhost:8087")
            .maxAge(1800);
    }
}
