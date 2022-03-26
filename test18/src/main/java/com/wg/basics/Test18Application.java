package com.wg.basics;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing//开启批处理
public class Test18Application {

    public static void main(String[] args) {
        SpringApplication.run(Test18Application.class, args);
    }

}
