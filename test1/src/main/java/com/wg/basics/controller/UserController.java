package com.wg.basics.controller;

import com.wg.basics.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class UserController {
    @Autowired
    private User user;
    @GetMapping("/user")
    public String user(){
        return user.toString();
    }
}
