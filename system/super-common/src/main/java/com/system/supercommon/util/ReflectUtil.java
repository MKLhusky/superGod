package com.system.supercommon.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Description: 反射工具类
 * @Author: Mr. Dai
 * @Date: 2023/3/28 19:55
 **/
public class ReflectUtil {



    /**
     * @Author: Mr. Dai
     * @Description:  给指定名称字段赋值
     * @Date: 22:11 2023/3/28
     * @param filed
     * @param o
     * @param value
     **/
    public static void setValue(String filed,Object o,Object value){
        Field field = getField(o, filed);
        field.setAccessible(true);
        try {
            field.set(o,value);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * @Author: Mr. Dai
     * @Description:  给指定字段赋值
     * @Date: 22:11 2023/3/28
     * @param field
     * @param o
     * @param value
     **/
    public static void setValue(Field field,Object o,Object value){
        field.setAccessible(true);
        try {
            field.set(o,value);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Author: Mr. Dai
     * @Description:  获取属性值
     * @Date: 19:58 2023/3/28
     * @param field
     * @param o
     **/
    public static Object getValue(Field field,Object o){
        try {
            field.setAccessible(true);
            Object fieldValue = field.get(o);
            return fieldValue;
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Author: Mr. Dai
     * @Description:  获取所有field
     * @Date: 19:59 2023/3/28
     * @param obj
     **/
    public static Field[] getFields(Object obj){
        return obj.getClass().getDeclaredFields();
    }

    /**
     * @Author: Mr. Dai
     * @Description:  获取指定名称的field
     * @Date: 20:03 2023/3/28
     * @param obj
     * @param fieldName
     **/
    public static Field getField(Object obj,String fieldName){
        try {
            return obj.getClass().getField(fieldName);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Author: Mr. Dai
     * @Description:  获取所有method
     * @Date: 19:59 2023/3/28
     * @param obj
     **/
    public static Method[] getMethods(Object obj){
        return obj.getClass().getDeclaredMethods();
    }

    /**
     * @Author: Mr. Dai
     * @Description:  根据指定名称，获取无参方法
     * @Date: 20:00 2023/3/28
     * @param obj
     * @param name
     **/
    public static Method getMethod(Object obj,String name){
        return getMethod(obj,name,null);
    }

    /**
     * @Author: Mr. Dai
     * @Description:  根据指定名称，获取有参方法
     * @Date: 20:00 2023/3/28
     * @param obj
     * @param name
     **/
    public static Method getMethod(Object obj,String name,Class... param){
        try {
            Method declaredMethod = obj.getClass().getDeclaredMethod(name, param);
            return declaredMethod;
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

}
