package org.fd.jdbc;

import com.system.supercommon.util.ReflectUtil;

import java.lang.reflect.Field;

/**
 * Description: 全部字段 不忽略
 *
 * @author Mr. Dai
 * @date 2023/3/28 14:53
 */
public class AllNotIgnore<T> extends HandlerField<T,Object> {
    @Override
    protected HandlerField handler(T t) {
        for (Field declaredField : ReflectUtil.getFields(t)) {
            Object o = ReflectUtil.getValue(declaredField,t);
            if(null!=o){
                this.addField(declaredField.getName());
            }

        }
        return this;
    }
}
