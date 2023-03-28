package org.fd.jdbc;

/**
 * Description: 查询条件
 *
 * @author Mr. Dai
 * @date 2023/3/28 17:59
 */
public class SqlCondition {

    private String name;


    private Object value;

    public String getName() {
        return name;
    }

    public SqlCondition setName(String name) {
        this.name = name;
        return this;
    }

    public Object getValue() {
        return value;
    }

    public SqlCondition setValue(Object value) {
        this.value = value;
        return this;
    }
}
