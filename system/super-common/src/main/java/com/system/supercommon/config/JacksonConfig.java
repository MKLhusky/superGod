package com.system.supercommon.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.supercommon.util.spring.JsonEnumDeserialize;
import com.system.supercommon.util.spring.JsonEnumSerialize;
import com.system.supercommon.util.spring.JsonLongDeserialize;
import com.system.supercommon.util.spring.JsonLongSerialize;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * @Description: jackson 配置类
 * @Author: Mr. Dai
 * @Date: 2023/3/29 20:42
 **/
@Configuration

public class JacksonConfig {

    @ConditionalOnClass(ObjectMapper.class)
    @Bean
    @Primary
    public ObjectMapper httpMessageConverter(Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder){
        /**序列化枚举处理*/
        jackson2ObjectMapperBuilder.serializerByType(Enum.class,new JsonEnumSerialize());
        jackson2ObjectMapperBuilder.deserializerByType(Enum.class,new JsonEnumDeserialize());

        /**序列化Long处理*/
        jackson2ObjectMapperBuilder.serializerByType(Long.class,new JsonLongSerialize());
        jackson2ObjectMapperBuilder.deserializerByType(Long.class,new JsonLongDeserialize());
        return jackson2ObjectMapperBuilder.build();
    }
}