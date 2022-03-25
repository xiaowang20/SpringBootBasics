package com.wg.basics;

import com.wg.basics.entity.Role;
import com.wg.basics.entity.User;
import com.wg.basics.mapper.UserMapper;
import com.wg.basics.service.RoleService;
import com.wg.basics.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.awt.print.Book;

@SpringBootTest
class Test13ApplicationTests {
   @Autowired
    UserService userService;

   @Autowired
    RoleService roleService;
    @Test
    void contextLoads() {

        int register = userService.register();
        System.out.println("注册成功：" + register);
    }

@Test
    void getUser(){
    User admin = userService.getUser("admin");
    System.out.println("成功获取用户名：" + admin);
}

@Test
    void update(){
    System.out.println("更新成功：" + userService.updateUserId());
}

@Test
    void addRole(){
    Role role = new Role();
//    role.setName("ROLE_dba");
//    role.setNameZh("数据库管理员");

//    role.setName("ROLE_admin");
//    role.setNameZh("系统管理员");

    role.setName("ROLE_user");
    role.setNameZh("用户");
    System.out.println("成功添加角色：" + roleService.addRole(role));
}
}
