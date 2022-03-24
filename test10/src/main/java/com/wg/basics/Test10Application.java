package com.wg.basics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Test10Application {

    public static void main(String[] args) {
        SpringApplication.run(Test10Application.class, args);
    }

}
