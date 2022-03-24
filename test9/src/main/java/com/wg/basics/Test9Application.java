package com.wg.basics;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@MapperScan(value = "com.wg.basics.mapper")
public class Test9Application {

    public static void main(String[] args) {
        SpringApplication.run(Test9Application.class, args);
    }

}
