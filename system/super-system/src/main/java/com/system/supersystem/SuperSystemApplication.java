package com.system.supersystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SuperSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SuperSystemApplication.class, args);
    }

}
