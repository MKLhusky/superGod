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


        //找到枚举属性是Integer类型的名称

        String methodSecondName =null;

        for (Object enumConstant : enumConstants) {
            Class aClass = enumConstant.getClass();

            if(StringUtils.isBlank(methodSecondName)){
                String codeName = null;
                for (Field declaredField : ReflectUtil.getFields(enumConstant)) {
                    if (declaredField.getType().equals(Integer.class)) {
                        codeName = declaredField.getName();
                        break;
                    }
                }

                if (null == codeName || codeName.trim().length() == 0) {
                    throw new RuntimeException(String.format("获取不到属性的get方法: :%s", codeName));
                }
                methodSecondName=StringUtils.toFirstUpperCase(codeName);
            }

            //反射获取get方法
            Method method = null;
            try {
                method = ReflectUtil.getMethod(enumConstant,String.format("get%s", methodSecondName));
                Object invoke = method.invoke(enumConstant);
                if (invoke.equals(code)) {
                    result = (T) enumConstant;
                    break;
                }
            }  catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return result;
    }
}
