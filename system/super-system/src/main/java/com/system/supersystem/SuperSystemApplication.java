package com.system.supersystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication(scanBasePackages = {"com.system"},exclude = {DataSourceAutoConfiguration.class})
@ConfigurationPropertiesScan(basePackages = {"com.system","com.alibaba.druid.spring.boot.autoconfigure"})
public class SuperSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SuperSystemApplication.class, args);
    }

}
