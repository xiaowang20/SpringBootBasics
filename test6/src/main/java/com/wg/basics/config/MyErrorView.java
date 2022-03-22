package com.wg.basics.config;

import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 自定义error视图
 * 无论时报什么错都会跳转到我们自定义的页面
 */
//@Component
public class MyErrorView implements ErrorViewResolver {
    @Override
    public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> model) {
        ModelAndView mv = new ModelAndView("customError");
        mv.addObject("customMsg","错误");
        mv.addAllObjects(model);
        return mv;
    }
}
