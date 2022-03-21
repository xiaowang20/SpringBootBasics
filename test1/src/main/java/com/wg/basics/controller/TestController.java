package com.wg.basics.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class TestController {
    @GetMapping(value = "/hello")
    public String hello(){
        return "hello";
    }
}
