package com.system.supercommon.util.spring;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.system.supercommon.util.EnumUtils;
import lombok.Setter;
import java.io.IOException;


/**
 * @Description: json枚举反序列化处理器
 * @Author: Mr. Dai
 * @Date: 2023/3/29 20:15
 **/

@Setter
public class JsonEnumDeserialize<T extends Enum<?>>  extends JsonDeserializer<Enum<?>> implements ContextualDeserializer {


    public JsonEnumDeserialize(Class aClass){
        this.rawClass=aClass;
    }

    public JsonEnumDeserialize(){}
    private Class rawClass;

    @Override
    public Enum<?> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        if(null!=rawClass&&rawClass.isEnum()){
            String value = jsonParser.getText();
            Integer code = Integer.valueOf(value);
            return EnumUtils.getEnum(rawClass, code);
        }
        return null;
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
        if (deserializationContext.getContextualType()!=null) {
            return new JsonEnumDeserialize<>(deserializationContext.getContextualType().getRawClass());
        }
        return new JsonEnumDeserialize<>(beanProperty.getMember().getType().getRawClass());
    }
}
