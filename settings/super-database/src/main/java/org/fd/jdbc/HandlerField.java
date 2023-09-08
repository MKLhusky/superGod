package org.fd.jdbc;

import com.system.supercommon.util.ReflectUtil;

import java.beans.Introspector;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

/**
 * Description: 忽略抽象父类
 *
 * @author Mr. Dai
 * @date 2023/3/28 14:07
 */

public abstract class HandlerField<T,R> {


    Set<String> fields=new HashSet<>();

    //结果集
    private Set<String> exclude=new HashSet<>();

    //获取字段的lambda集合
    TypeFunction<T,R>[] typeFunctions;

    public boolean verify(Field field){
       return Modifier.isStatic(field.getModifiers())||Modifier.isFinal(field.getModifiers());
    }


    public HandlerField(TypeFunction<T,R>... typeFunctions){
        this.typeFunctions=typeFunctions;
    }

    private void getFields(){
        Set<String> set=new HashSet<>();
        for (TypeFunction<T, R> typeFunction : this.typeFunctions) {
            String lambdaColumn = TypeFunction.getLambdaColumn(typeFunction);
            set.add(lambdaColumn);
        }
        this.fields=set;
    }

    //交由子类实现
    protected abstract HandlerField handler(T t);


    protected HandlerField execute(T t){
        getFields();
        return handler(t);
    }

    //获取空也要忽略的字段
    protected Set<String> getResultFields(){
        return this.exclude;
    }

    protected void addField(String field){
        this.exclude.add(field);
    }


    /**
     * @Description:  获取字段名称的lambda接口
     * @author Mr. Dai
     * @date 2023/3/28 14:09
     */
    public interface TypeFunction<T,R> extends Serializable, Function<T,R> {
        //获取lambda
        static String getLambdaColumn(Serializable lambda){
            Method writeReplace = null;
            try {
                //jdk 会给实现Serializable 的lambda 自动增加一个writeReplace方法
                writeReplace = ReflectUtil.getMethod(lambda,"writeReplace");
                writeReplace.setAccessible(true);
                //转化为SerializedLambda  jdk为lambda专门写了一个处理函数的序列化类
                SerializedLambda serializedLambda = (SerializedLambda) writeReplace.invoke(lambda);
                //获取函数名称
                String getter = serializedLambda.getImplMethodName();
                //去掉get 获取字段名称
                String name = Introspector.decapitalize(getter.replace("get", ""));
                return name;
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
