package com.wg.basics.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class TestController {
    @GetMapping(value = "/hello")
    public String hello(){
        return "hello";
    }
}
