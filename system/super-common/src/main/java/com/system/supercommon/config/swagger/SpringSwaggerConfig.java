package com.system.supercommon.config.swagger;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 采用springdoc swagger版本
 * @Author: Mr. Dai
 * @Date: 2023/4/2 22:32
 **/
@Configuration
public class SpringSwaggerConfig {

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .info(new Info().title("superGod")
                        .description("superGod API接口文档管理")
                )/*.externalDocs(new ExternalDocumentation()
                        .description("superGod all interface")
                        .url("暂无"))*/;
    }


//    @Bean
//    public GroupedOpenApi userApi(){
//        return GroupedOpenApi.builder()
//                .group("用户模块")
//                .pathsToMatch("/user")
//                .build();
//    }
}
