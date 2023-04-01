package org.fd.jdbc;

/**
 * Description: 选择某些字段
 *
 * @author Mr. Dai
 * @date 2023/3/28 15:00
 */
public class FieldSelect<T> extends HandlerField<T,Object> {

    public FieldSelect(TypeFunction<T,Object>... typeFunctions){
        super(typeFunctions);
        if(null==typeFunctions||typeFunctions.length<1){
            throw new IllegalArgumentException("查询固定字段处理器,最少选择一个字段查询");
        }
    }
    @Override
    protected HandlerField handler(T t) {
        for (String field : this.fields) {
            this.addField(field);
        }
        return this;
    }

}
