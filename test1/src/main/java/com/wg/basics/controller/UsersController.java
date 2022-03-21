package com.wg.basics.controller;

import com.wg.basics.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class UsersController {
    @Autowired
    private Users users;
    @GetMapping("/users")
    public String users(){
        return users.toString();
    }
}
