package org.fd.jdbc;

import java.lang.annotation.*;

/**
 * @Description: 配合数据库jdbc工具类使用的注解  标注表名称
 * @Author: Mr. Dai
 * @Date: 2023/7/18 20:17
 **/
@Target({ElementType.TYPE,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TableName {
    public String value() default "";
}
