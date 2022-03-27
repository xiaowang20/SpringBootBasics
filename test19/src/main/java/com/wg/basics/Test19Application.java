package com.wg.basics;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer
public class Test19Application {

    public static void main(String[] args) {
        SpringApplication.run(Test19Application.class, args);
    }

}
