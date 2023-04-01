package org.fd.jdbc;

import java.lang.reflect.Field;

/**
 * Description: 字段忽略 查询使用
 *
 * @author Mr. Dai
 * @date 2023/3/28 14:37
 */
public class FieldExclude<T> extends HandlerField<T,Object> {

    public FieldExclude(TypeFunction<T,Object>... typeFunctions){
        super(typeFunctions);
    }
    @Override
    protected HandlerField handler(T t) {

        Class<?> tempClass = t.getClass();
        while (null!=tempClass&&!tempClass.equals(Object.class)){
            for (Field declaredField : tempClass.getDeclaredFields()) {
                if (!this.fields.contains(declaredField.getName())) {
                    this.addField(declaredField.getName());
                }
            }
            tempClass=tempClass.getSuperclass();
        }
        return this;
    }
}
