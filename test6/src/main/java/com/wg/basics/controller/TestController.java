package com.wg.basics.controller;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;


@RestController
public class TestController {

    /**
     * 输出值：key=info.value=注入的map。
     * @param model
     */
    @GetMapping("/test")
    public void test(Model model){
        Map<String, Object> map = model.asMap();
        Set<String> keySet = map.keySet();
        Iterator<String> iterator = keySet.iterator();
        while(iterator.hasNext()){
            String key = iterator.next();
            Object value = map.get(key);
            System.out.println(key + "==" + value);
        }
    }
}
