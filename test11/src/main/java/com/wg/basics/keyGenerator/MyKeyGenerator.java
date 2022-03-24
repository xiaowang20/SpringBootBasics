package com.wg.basics.keyGenerator;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 自定义缓存key的生成器
 * 如果spring提供的key不够，可以自己定义key的生成器
 */
@Component
public class MyKeyGenerator implements KeyGenerator {
    @Override
    public Object generate(Object target, Method method, Object... params) {
        return Arrays.toString(params);
    }

}
