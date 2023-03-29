package com.system.supercommon.util.spring;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @Description: Long 长类型序列化处理
 * @Author: Mr. Dai
 * @Date: 2023/3/29 22:31
 **/
public class JsonLongSerialize extends JsonSerializer<Long> {
    @Override
    public void serialize(Long aLong, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (null!=aLong) {
            jsonGenerator.writeString(aLong.toString());
        }else{
            jsonGenerator.writeString((String) null);
        }
    }
}
