package org.fd.jdbc;


import com.system.supercommon.util.ReflectUtil;
import com.system.supercommon.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.temporal.TemporalAdjuster;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Description: bean工具类
 *
 * @author Mr. Dai
 * @date 2023/3/28 14:56
 */
public class SqlBeanUtils {


    private static String SUFFIX="PO";

    protected static Set<String> getFields(Object t, HandlerField handlerField){
        if (null==handlerField) {
            return new AllNotIgnore<>().execute(t).getResultFields();
        }
        return handlerField.execute(t).getResultFields();
    }


    /**
     * @Description:  获取对象id
     *  //TODO  有强烈约束 项目规范工具  id 字段为寻找到的第一个字段名称为id  或 Id后缀的
     * @param t
     * @return java.lang.String
     * @author Mr. Dai
     * @date 2023/3/28 15:13
     */
    protected static String getId(Object t){
        for (Field declaredField : ReflectUtil.getFields(t)) {
            String name = declaredField.getName();
            if (name.endsWith("id")||name.endsWith("Id")) {
                return name;
            }
        }
        return null;
    }

    /**
     * @Description:   获取数据库名称
     * @param cls 类型
     * @param suffix 后缀 默认PO
     * @return java.lang.String
     * @author Mr. Dai
     * @date 2023/3/28 17:21
     */
    protected static String getTable(Class cls,String suffix){
        String simpleName = cls.getSimpleName();
        simpleName= StringUtils.cutSuffix(simpleName,(null!=suffix&&suffix.trim().length()>0)?suffix:SUFFIX);
        return StringUtils.toSnake(simpleName);
    }

    /**
     * @Description:  获取对象有值的字段名称和值
     * @param t
     * @return java.util.List<org.daiqimeng.example.sqlutil.SelectCondition>
     * @author Mr. Dai
     * @date 2023/3/28 18:02
     */
    protected static<T> List<SqlCondition> getCondition(T t){

        List<SqlCondition> conditions=new ArrayList<>();

        Class temp=t.getClass();
        while (null!=temp&&!temp.equals(Object.class)){
            for (Field declaredField : temp.getDeclaredFields()) {
                Object o = ReflectUtil.getValue(declaredField,t);
                if (null!=o) {

                    //时间类型暂时过滤掉
                    if(Date.class.isAssignableFrom(o.getClass())|| TemporalAdjuster.class.isAssignableFrom(o.getClass())){
                        continue;
                    }

                    SqlCondition selectCondition=new SqlCondition();
                    selectCondition.setName(declaredField.getName());
                    if (o.getClass().isEnum()) {
                        //枚举单独处理               getCode方法暂时写死
                        Method getCode = ReflectUtil.getMethod(o, "getCode");
                        try {
                            selectCondition.setValue(getCode.invoke(o));
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        } catch (InvocationTargetException e) {
                            throw new RuntimeException(e);
                        }
                    }else{
                        selectCondition.setValue(o);
                    }
                    conditions.add(selectCondition);
                }
            }
            temp=temp.getSuperclass();
        }
        return conditions;
    }





}
