package com.wg.basics.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("getUser")
    public String getUser(Integer id){
       return userService.getUser(id);
    }

    @GetMapping("deleteUser")
    public void deleteUser(Integer id){
        userService.deleteUser(id);
    }
}
