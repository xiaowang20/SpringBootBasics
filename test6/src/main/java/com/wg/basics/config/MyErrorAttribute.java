package com.wg.basics.config;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * 自定义error属性配置
 * 只需要实现一个ErrorAttribute类即可，知道DefaultErrorAttribute类是ErrorAttribute类的子类，所以只用继承DefaultErrorAttribute类。
 * 实现getErrorAttributes()方法
 */
//@Component
public class MyErrorAttribute extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, options);
        errorAttributes.put("customMsg","出错了，快去找bug吧！");
        return errorAttributes;
    }
}
