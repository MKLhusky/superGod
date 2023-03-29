package com.system.supercommon.util.spring;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.system.supercommon.util.StringUtils;

import java.io.IOException;

/**
 * @Description: Long 长类型反序列化处理
 * @Author: Mr. Dai
 * @Date: 2023/3/29 22:28
 **/
public class JsonLongDeserialize extends JsonDeserializer<Long> {
    @Override
    public Long deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        if (StringUtils.isNoneBlank(jsonParser.getText())) {
            return Long.valueOf(jsonParser.getText());
        }
        return null;
    }
}
