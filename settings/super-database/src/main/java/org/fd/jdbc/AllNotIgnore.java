package org.fd.jdbc;

import com.system.supercommon.bean.ParentPO;
import com.system.supercommon.util.ReflectUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * Description: 全部字段 不忽略
 *
 * @author Mr. Dai
 * @date 2023/3/28 14:53
 */
public class AllNotIgnore<T> extends HandlerField<T,Object> {
    @Override
    protected HandlerField handler(T t) {
        Class<?> tempClass = t.getClass();
        while (null!=tempClass&&!tempClass.equals(Object.class)){
            for (Field declaredField : tempClass.getDeclaredFields()) {
                Object o = ReflectUtil.getValue(declaredField,t);
                if(null!=o){
                    this.addField(declaredField.getName());
                }

            }
            tempClass=tempClass.getSuperclass();
        }
        return this;
    }
}
