package com.wg.basics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class Test8Application {

    public static void main(String[] args) {
        SpringApplication.run(Test8Application.class, args);
    }

}
