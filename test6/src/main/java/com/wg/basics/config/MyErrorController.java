package com.wg.basics.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resources;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 完全自定义error
 * 由于BasicsErrorController没有无参构造，创建BasicErrorController实例时要传参，使用@Autowired
 *
 */
@Controller

public class MyErrorController extends BasicErrorController {


    @Autowired
    public MyErrorController(ErrorAttributes errorAttributes,
                             ServerProperties serverProperties,
                             List<ErrorViewResolver> errorViewResolvers) {
        super(errorAttributes,serverProperties.getError(), errorViewResolvers);
    }


    /**
     * 自定义errorView
     * @param request
     * @param response
     * @return
     */
    @Override
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        HttpStatus status = getStatus(request);
        Map<String, Object> model = getErrorAttributes(request, getErrorAttributeOptions(request, MediaType.TEXT_HTML));
        model.put("customMsg","完全自定义");
        ModelAndView mv = new ModelAndView("customErrorSuper",model,status);
        return mv;
    }

    /**
     * 自定义errorAttribute
     * @param request
     * @return
     */
    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        Map<String, Object> body = getErrorAttributes(request, getErrorAttributeOptions(request, MediaType.TEXT_HTML));
        body.put("custom","主体");
        HttpStatus status = getStatus(request);
        ResponseEntity<Map<String, Object>> responseEntity = new ResponseEntity<>(body, status);
        return responseEntity;
    }
}
