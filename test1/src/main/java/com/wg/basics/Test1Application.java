package com.wg.basics;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Test1Application {
    public static void main(String[] args) {
        SpringApplication.run(Test1Application.class, args);
//        SpringApplicationBuilder springApplicationBuilder = new SpringApplicationBuilder(Test1Application.class);
//        springApplicationBuilder.bannerMode(Banner.Mode.OFF).run(args);
    }

}
