package com.wg.basics.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test2Controller {
    @GetMapping("/admin/hello")
    public String admin(){
        return "Hello admin";
    }
    @GetMapping("/db/hello")
    public String user(){
        return "Hello dba";
    }
    @GetMapping("/user/hello")
    public String user2(){
        return "Hello user";
    }
}
