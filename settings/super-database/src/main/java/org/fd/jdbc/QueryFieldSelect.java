package org.fd.jdbc;

/**
 * Description: 选择某些字段 查询使用
 *
 * @author Mr. Dai
 * @date 2023/3/28 15:00
 */
public class QueryFieldSelect<T> extends HandlerField<T,Object> {

    public QueryFieldSelect(TypeFunction<T,Object>... typeFunctions){
        super(typeFunctions);
    }
    @Override
    protected HandlerField handler(T t) {
        for (String field : this.fields) {
            this.addField(field);
        }
        return this;
    }

}
