package com.wg.basics.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用于测试security框架
 * 注：
 *  1.在项目中加入security依赖后整个项目就会被保护起来。
 *  2.然后在调用接口时会先进入一个登录页面。
 *  3.用户名默认为user，密码是项目启动后在Run里面生成随机密码。
 *  4.登陆成功后调用项目接口返回应当的结果。
 *  5.当然也可以自定义用户名、和密码:
 *      1.在application.properties中配置
 *      2.使用java代码实现（继承WebSecurityConfigurerAdapter）基于内存认证。
 *      3.但是这种方法有局限性，很多受保护资源都是默认的。我们要自定义的多一点就要多重写一些方法。
 */
@RestController
@RequestMapping("/admin")
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "Hello";
    }
}
