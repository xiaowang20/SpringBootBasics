package com.wg.basics.controller;

import com.wg.basics.entity.ManUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class ManUserController {
    @Autowired
    private ManUser user;
    @GetMapping("/manUser")
    public String manUser(){
        return user.toString();
    }
}
