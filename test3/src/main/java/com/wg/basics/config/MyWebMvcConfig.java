package com.wg.basics.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;
import java.util.List;

/**
 * 实现WebMvcConfigurer是另一种方法
 * 自定义fastJson和Gson都可以
 * 但是不推荐使用Gson（较为麻烦）
 * 注：同时配置WebMvcConfigurer和FastJson,会先走FastJson配置。
 */
@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter converter =new FastJsonHttpMessageConverter();
        FastJsonConfig config = new FastJsonConfig();
        config.setDateFormat("yyyy-MM-dd");
        config.setCharset(Charset.forName("UTF-8"));
        /**
         * 1.是否在生成的JSON中输出类名
         * 2.是否输出value为null的数据
         * 3.生成JSON格式化
         * 4.空集合输出[],而不是null
         * 5.空集合输出"",而不是null
         */
        config.setSerializerFeatures(
                SerializerFeature.WriteClassName,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.PrettyFormat,
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteNullStringAsEmpty
        );
        converter.setFastJsonConfig(config);
        converters.add(converter);
    }
}
