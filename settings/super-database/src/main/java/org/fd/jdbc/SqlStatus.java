package org.fd.jdbc;

/**
 * Description: sql操作状态
 *
 * @author Mr. Dai
 * @date 2023/3/28 15:44
 */
public enum SqlStatus {
    QUERY(0), //查
    UPDATE(1), //改
    INSERT(2), //增
    DELETE(3); //删
    private Integer code;

    SqlStatus(Integer code){
        this.code=code;
    }

    public Integer getCode() {
        return code;
    }
}
