package com.wg.basics.aop;

import org.springframework.stereotype.Service;

/**
 * 创建两个方法
 */
@Service
public class UserService {

    public String getUser(Integer id){
        System.out.println("getUser");
        return "name";
    }
    public void deleteUser(Integer id){
        System.out.println("delete");
    }
}
