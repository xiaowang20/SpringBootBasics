package com.wg.basics.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import java.lang.reflect.Modifier;

/**
 * 可以立即使用。
 * 注：SpringBoot项目没有提供GsonHttpMessageConverter时，
 *    会使用默认的GsonHttpMessageConverter。
 *    所以要自定义GsonHttpMessageConverter才能达到我们自己想要的效果。
 */
@Configuration
public class GsonConfiguration {
    @Bean
    GsonHttpMessageConverter gsonHttpMessageConverter(){
        GsonHttpMessageConverter converter = new GsonHttpMessageConverter();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.excludeFieldsWithModifiers(Modifier.PROTECTED);//忽略protected字段。
        gsonBuilder.setDateFormat("yyyy-MM-dd");
        Gson gson = gsonBuilder.create();
        converter.setGson(gson);
        return converter;
    }
}
