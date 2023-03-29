package com.system.supercommon.util.spring;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.system.supercommon.util.ReflectUtil;
import org.apache.commons.lang3.ObjectUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Description: json枚举序列化器
 * @Author: Mr. Dai
 * @Date: 2023/3/29 20:31
 **/
public class JsonEnumSerialize<T extends Enum<?>> extends JsonSerializer<Enum<?>>{


    @Override
    public void serialize(Enum<?> anEnum, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (null!=anEnum) {
            Method getCode = ReflectUtil.getMethod(anEnum, "getCode");
            try {
                Object invoke = getCode.invoke(anEnum);
                jsonGenerator.writeString(invoke.toString());
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }else{
            jsonGenerator.writeString((String) null);
        }
    }
}
