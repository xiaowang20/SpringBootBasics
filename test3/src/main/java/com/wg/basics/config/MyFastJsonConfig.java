package com.wg.basics.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.Charset;

/**
 * fastJson不能直接使用，要提供相应的HttpMessageConverter.
 * 配置完之后还需要配置响应码，不配，则会乱码。
 * 在application.properties中配置
 */
@Configuration
public class MyFastJsonConfig {
    @Bean
    FastJsonHttpMessageConverter fastJsonHttpMessageConverter(){
        FastJsonHttpMessageConverter converter =new FastJsonHttpMessageConverter();
        FastJsonConfig config = new FastJsonConfig();
        config.setDateFormat("yyyy-MM-dd:HH-mm-ss");
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
        return converter;
    }
}
