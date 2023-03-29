package com.system.supercommon.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.system.supercommon.util.spring.JsonEnumDeserialize;
import com.system.supercommon.util.spring.JsonEnumSerialize;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * @Description: jackson 配置类
 * @Author: Mr. Dai
 * @Date: 2023/3/29 20:42
 **/
@Configuration

public class JackSonConfig {

    @ConditionalOnClass(ObjectMapper.class)
    @Bean
    @Primary
    public ObjectMapper httpMessageConverter(Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder){
        jackson2ObjectMapperBuilder.serializerByType(Enum.class,new JsonEnumSerialize());
        jackson2ObjectMapperBuilder.deserializerByType(Enum.class,new JsonEnumDeserialize());
        return jackson2ObjectMapperBuilder.build();
    }
}
