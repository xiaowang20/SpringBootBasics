package com.wg.basics.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局配置(不能处理容器异常：例：Filter)
 * 使用@ControllerAdvice即可
 */
@ControllerAdvice
public class GlobalConfig {
    /**
     * 使用 @ModelAttribute注解
     * @return
     */
    @ModelAttribute(value = "info")
    public Map<String,String> userInfo(){
        HashMap<String,String> map = new HashMap<>();
        map.put("username","金庸");
        map.put("sex","男");
        return map;
    }

    /**
     * 使用@InitBinder注解
     * @param binder
     */
    @InitBinder(value = "a")
    public void init1(WebDataBinder binder){
        binder.setFieldDefaultPrefix("a.");
    }

    @InitBinder(value = "b")
    public void init2(WebDataBinder binder){
        binder.setFieldDefaultPrefix("b.");
    }


}
