package com.system.supercommon.util;


import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Description:
 *
 * @author Mr. Dai
 * @date 2023/3/28 10:09
 */
public class EnumUtils {

    public static <T extends Enum<T>> T getEnum(Class<T> t, Integer code) {

        //获取所有枚举对象
        Object[] enumConstants = t.getEnumConstants();
        T result = null;

        for (Object enumConstant : enumConstants) {
            Class aClass = enumConstant.getClass();
            //获取枚举所有属性
            Field[] declaredFields = aClass.getDeclaredFields();

            String codeName = null;
            //找到枚举属性是Integer类型的名称
            for (Field declaredField : declaredFields) {
                if (declaredField.getType().equals(Integer.class)) {
                    codeName = declaredField.getName();
                }
            }

            if (null == codeName || codeName.trim().length() == 0) {
                throw new IllegalArgumentException("Error obtaining enumeration object: Field with attribute value type Integer was not found");
            }


            String methodSecondName = StringUtils.toFirstUpperCase(codeName);

            //反射获取get方法
            Method method = null;
            try {
                method = aClass.getMethod(String.format("get%s", methodSecondName));
                Object invoke = method.invoke(enumConstant);
                if (invoke.equals(code)) {
                    result = (T) enumConstant;
                    break;
                }
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(String.format("获取不到属性的get方法: :%s", codeName));
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return result;
    }
}
