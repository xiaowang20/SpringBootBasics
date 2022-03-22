package com.wg.basics.controller;

import org.springframework.web.bind.annotation.*;

/**
 * 使用@CorssOrigin作用在方法上，解决跨域问题。
 */
@RestController
@RequestMapping("test")
public class testController {

//    @CrossOrigin(value = "http://localhost:8087",maxAge = 1800,allowedHeaders = "*")
    @PostMapping("/addBook")
    public String addBook(String name){
        return "接收数据：" + name;
    }
//    @CrossOrigin(value = "http://localhost:8087",maxAge = 1800,allowedHeaders = "*")
    @DeleteMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable Long id){
        return String.valueOf(id);
    }
}
