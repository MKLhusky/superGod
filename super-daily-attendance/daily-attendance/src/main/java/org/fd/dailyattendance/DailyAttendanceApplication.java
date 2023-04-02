package org.fd.dailyattendance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication(scanBasePackages = {"org.fd.**", "com.system.**"},exclude = {DataSourceAutoConfiguration.class})
@ConfigurationPropertiesScan(basePackages = {"org.fd.**", "com.system.**", "com.alibaba.druid.spring.boot.autoconfigure"})
public class DailyAttendanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DailyAttendanceApplication.class, args);
    }

}
