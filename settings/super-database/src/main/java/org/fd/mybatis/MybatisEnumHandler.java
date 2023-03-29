package org.fd.mybatis;

import com.system.supercommon.util.EnumUtils;
import com.system.supercommon.util.ReflectUtil;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Description:
 * @Author: Mr. Dai
 * @Date: 2023/3/29 21:37
 **/
public class MybatisEnumHandler<E extends Enum<E>> extends BaseTypeHandler<E> {


    private Class<E> type;

    //构造 盲猜mybatis  会通过构造告诉你要处理哪个枚举
    public MybatisEnumHandler(Class<E> type){
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;
    }


    //枚举不为空如何存数据库处理方法
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        Method getCode = ReflectUtil.getMethod(parameter, "getCode");
        try {
            Object invoke = getCode.invoke(parameter);
            ps.setObject(i,invoke);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int code = rs.getInt(columnName);
        return rs.wasNull()?null: EnumUtils.getEnum(type,code);
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int code = rs.getInt(columnIndex);
        return rs.wasNull()?null: EnumUtils.getEnum(type,code);
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int code = cs.getInt(columnIndex);
        return cs.wasNull()?null:EnumUtils.getEnum(type,code);
    }
}
