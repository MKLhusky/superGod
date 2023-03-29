package org.fd.jdbc;

import com.system.supercommon.bean.ParentPO;
import com.system.supercommon.util.ReflectUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * Description: 处理为空也不忽略的字段  新增和修改使用
 *
 * @author Mr. Dai
 * @date 2023/3/28 11:33
 */
public class EmptyNotIgnore<T> extends HandlerField<T,Object> {


    public EmptyNotIgnore(TypeFunction<T,Object>... typeFunctions){
        super(typeFunctions);
    }


    @Override
    protected HandlerField handler(T t) {

        Class<?> tempClass = t.getClass();
        while (null!=tempClass&&!tempClass.equals(Object.class)){
            for (Field declaredField : tempClass.getDeclaredFields()) {
                Object o = ReflectUtil.getValue(declaredField,t);
                String name = declaredField.getName();
                if(null!=o){
                    addField(name);
                }else{
                    //如果为空 判断是否在不需要忽略的字段中
                    if (this.fields.contains(name)) {
                        //如果存在 也要加入
                        addField(name);
                    }
                }

            }
            tempClass=tempClass.getSuperclass();
        }
        return this;
    }
}
