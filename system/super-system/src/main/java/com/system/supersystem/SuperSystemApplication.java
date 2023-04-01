package com.system.supersystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication(scanBasePackages = {"com.system.**","org.fd.**"})
@ConfigurationPropertiesScan(basePackages = {"com.system.**","org.fd.**","com.alibaba.druid.spring.boot.autoconfigure"})
@MapperScan(basePackages = {"com.system.**.dao"})
public class SuperSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SuperSystemApplication.class, args);
    }

}
