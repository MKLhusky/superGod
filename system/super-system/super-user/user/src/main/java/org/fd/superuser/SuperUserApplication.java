package org.fd.superuser;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication(scanBasePackages = {"com.system.**","org.fd.**"},exclude = {DataSourceAutoConfiguration.class})
@ConfigurationPropertiesScan(basePackages = {"com.system.**","org.fd.**","com.alibaba.druid.spring.boot.autoconfigure"})
@MapperScan(basePackages = {"com.system.**.dao","org.fd.**.dao"})
public class SuperUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(SuperUserApplication.class, args);
    }

}
