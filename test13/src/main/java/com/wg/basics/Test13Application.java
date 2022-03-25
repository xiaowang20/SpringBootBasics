package com.wg.basics;

import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.wg.basics.mapper")
public class Test13Application {

    public static void main(String[] args) {
        SpringApplication.run(Test13Application.class, args);
    }

}
