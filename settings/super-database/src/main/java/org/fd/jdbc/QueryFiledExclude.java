package org.fd.jdbc;

import com.system.supercommon.util.ReflectUtil;

import java.lang.reflect.Field;

/**
 * Description: 字段忽略 查询使用
 *
 * @author Mr. Dai
 * @date 2023/3/28 14:37
 */
public class QueryFiledExclude<T> extends HandlerField<T,Object> {

    public QueryFiledExclude(TypeFunction<T,Object>... typeFunctions){
        super(typeFunctions);
    }
    @Override
    protected HandlerField handler(T t) {
        for (Field declaredField : ReflectUtil.getFields(t)) {
            if (!this.fields.contains(declaredField.getName())) {
                this.addField(declaredField.getName());
            }
        }
        return this;
    }
}
