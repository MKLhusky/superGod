package org.fd.jdbc;

import com.system.supercommon.util.EnumUtils;
import com.system.supercommon.util.ReflectUtil;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.*;

/**
 * Description: 项目sql攻击类
 *
 * @author Mr. Dai
 * @date 2023/3/28 15:17
 */
@Slf4j
public class SqlUtils {

    private DataSource dataSource;

    public SqlUtils(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * @Author: Mr. Dai
     * @Description:  根据对象更新数据
     * @Date: 16:26 2023/4/1
     * @param t
     * @param fieldSelect 指定要作为条件的字段
     **/
    public <T> int update(@NotNull T t,FieldSelect<T> fieldSelect){
        return update(t,null,fieldSelect);
    }

    /**
     * @Author: Mr. Dai
     * @Description:
     * @Date: 17:16 2023/4/1
     * @param t
     * @param handlerField 为null 也要处理的字段
     * @param fieldSelect  指定要作为条件的字段
     **/
    public <T> int update(@NotNull T t,EmptyNotIgnore<T> handlerField,FieldSelect<T> fieldSelect){

        if(null==fieldSelect){
            throw new RuntimeException("更新必须指定条件字段");
        }

        Set<String> fields = SqlBeanUtils.getFields(t, handlerField);
        Set<String> selectField = SqlBeanUtils.getFields(t, fieldSelect);

        //删除掉作为条件的字段
        for (String s : selectField) {
            fields.remove(s);
        }
        if (fields.size()<1) {
            throw new RuntimeException("(去除条件)无可更新的字段");
        }
        //获取有值的字段
        List<SqlCondition> condition = SqlBeanUtils.getCondition(t);
        //用户条件编译取值的容器
        Map<String,Object> map=new HashMap<>();
        for (SqlCondition sqlCondition : condition) {
            map.put(sqlCondition.getName(),sqlCondition.getValue());
        }

        String[] fieldArr = fields.toArray(new String[0]);

        //获取条件字段
        String[] conditionField = selectField.toArray(new String[0]);

        //构造条件编译需要的条件数组
        SqlCondition[] temp=new SqlCondition[conditionField.length];
        for(int i=0;i<conditionField.length;i++){
            temp[i]=new SqlCondition().setName(conditionField[i]);
        }

        //获取表名称
        String table = SqlBeanUtils.getTable(t.getClass(), null);
        String sql = SqlAssembly.assembly(table, fieldArr, SqlStatus.UPDATE,temp);
        if (log.isInfoEnabled()) {
            log.info(sql);
        }
        try (Connection connection = getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            //更新的值 和条件值都需要在预编译中填充,所以2个按顺序合并
            String[] fieldsValue=new String[fieldArr.length+conditionField.length];

            System.arraycopy(fieldArr,0,fieldsValue,0,fieldArr.length);
            System.arraycopy(conditionField,0,fieldsValue,fieldArr.length,conditionField.length);

            for(int i=0;i<fieldsValue.length;i++){
                preparedStatement.setObject(i+1,map.get(fieldsValue[i]));
            }
            return preparedStatement.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Author: Mr. Dai
     * @Description: 根据指定对象插入数据
     * @Date: 16:10 2023/4/1
     * @param t
     **/
    public <T>int insert(@NotNull T t){
        return insert(t,null);
    }

    /**
     * @Author: Mr. Dai
     * @Description: 根据指定对象插入数据
     * @Date: 16:10 2023/4/1
     * @param t
     * @param handlerField 字段特殊处理
     **/
    public <T> int  insert(@NotNull T t,EmptyNotIgnore<T> handlerField){
        /*根据条件获取执行的字段  并转成数组*/
        Set<String> fields = SqlBeanUtils.getFields(t, handlerField);
        String[] fieldArr = fields.toArray(new String[0]);
        //获取表名称
        String table = SqlBeanUtils.getTable(t.getClass(), null);
        //组装sql
        String sql = SqlAssembly.assembly(table, fieldArr, SqlStatus.INSERT);


        List<SqlCondition> condition = SqlBeanUtils.getCondition(t);
        //存储字段值的容器
        Map<String,Object> map=new HashMap<>();
        for (SqlCondition sqlCondition : condition) {
            map.put(sqlCondition.getName(),sqlCondition.getValue());
        }

        try(Connection connection = getConnection()) {
            //获取预编译sql执行器
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //预编译赋值
            for(int i=0;i<fieldArr.length;i++){
                preparedStatement.setObject(i+1,map.get(fieldArr[i]));
            }
            if (log.isInfoEnabled()) {
                log.info(sql);
            }
            //执行sql
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Author: Mr. Dai
     * @Description:  根据条件删除数据
     * @Date: 15:41 2023/4/1
     * @param t
     **/
    public <T> int delete(@NotNull T t){
        List<SqlCondition> condition = SqlBeanUtils.getCondition(t);

        if (null==condition||condition.size()<1) {
            throw new RuntimeException("删除语句条件不能为null");
        }
        String table = SqlBeanUtils.getTable(t.getClass(), null);

        SqlCondition[] sqlConditions = condition.toArray(new SqlCondition[0]);
        String sql = SqlAssembly.assembly(table, null, SqlStatus.DELETE, sqlConditions);

        if (log.isInfoEnabled()) {
            log.info(sql);
        }

        try (Connection connection = getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for(int i=0;i<sqlConditions.length;i++){
                preparedStatement.setObject(i+1,sqlConditions[i].getValue());
            }
            return  preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    /**
     * @Description: 根据条件判断数据是否存在
     * @param t
     * @return boolean
     * @author Mr. Dai
     * @date 2023/3/29 15:29
     */
    public <T> boolean isExist(@NotNull T t){
        List<SqlCondition> condition = SqlBeanUtils.getCondition(t);
        if (null==condition||condition.size()<1) {
            throw new RuntimeException("条件不能为null");
        }

        String table = SqlBeanUtils.getTable(t.getClass(), null);
        StringBuffer buffer=new StringBuffer(String.format("select 1 from %s ",table));
        SqlCondition[] sqlConditions = condition.toArray(new SqlCondition[0]);
        SqlAssembly.assemblyCondition(buffer,sqlConditions);
        buffer.append(" limit 1");

        if (log.isInfoEnabled()) {
            log.info(buffer.toString());
        }
        ResultSet resultSet = null;
        try (Connection connection = getConnection()){

            PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
            for(int i=0;i<sqlConditions.length;i++){
                preparedStatement.setObject(i+1,sqlConditions[i].getValue());
            }
            resultSet= preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Author: Mr. Dai
     * @Description:  根据条件获取单个数据
     * @Date: 21:07 2023/3/28
     * @param t
     **/
    public <T>T selectOne(@NotNull T t){
        return selectOne(t,new FieldExclude<T>());
    }


    /**
     * @Author: Mr. Dai
     * @Description: 根据条件和字段处理器 获取单挑数据
     * @Date: 21:07 2023/3/28
     * @param t
     * @param handlerField
     **/
    public <T>T selectOne(@NotNull T t,HandlerField<T,Object> handlerField){

        //返回结果集
        List<T> tempList = selectList(t, handlerField);
        if(tempList.size()>1){
            throw new RuntimeException(String.format("查询一条数据,但是查询到了%s条",tempList.size()));
        }
        return tempList.size()>0?tempList.get(0):null;
    }

    /**
     * @Author: Mr. Dai
     * @Description:  根据条件查询集合
     * @Date: 22:18 2023/3/28
     * @param t
     **/
    public <T> List<T> selectList(@NotNull T t){
        return selectList(t,new FieldExclude<T>());
    }


    /**
     * @Author: Mr. Dai
     * @Description:  根据条件和字段处理器查询集合
     * @Date: 22:18 2023/3/28
     * @param t
     * @param handlerField
     **/
    public <T> List<T> selectList(@NotNull T t,HandlerField<T,Object> handlerField){
        //获取所有查询字段
        Set<String> fields = SqlBeanUtils.getFields(t, handlerField);
        String[] arrFields = fields.toArray(new String[0]);
        //构造条件
        List<SqlCondition> condition = SqlBeanUtils.getCondition(t);
        SqlCondition[] sqlConditions = condition.toArray(new SqlCondition[0]);
        //拼装sql
        String sql = SqlAssembly.assembly(SqlBeanUtils.getTable(t.getClass(), null), arrFields, SqlStatus.QUERY, sqlConditions);

        if (log.isInfoEnabled()) {
            log.info(sql);
        }


        try (Connection connection = getConnection()){
            //执行sql
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            for(int i=0;i<sqlConditions.length;i++){
                preparedStatement.setObject(i+1,sqlConditions[i].getValue());
            }
            ResultSet resultSet = preparedStatement.executeQuery();

            Class<T> aClass = (Class<T>) t.getClass();

            //获取临时结果集
            List<T> tempResult = assignValue(aClass, arrFields, resultSet);

            return tempResult;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Author: Mr. Dai
     * @Description: 根据查询结果集赋值
     * @Date: 21:42 2023/3/28
     * @param cls
     * @param field
     * @param set
     **/
    private <T> List<T> assignValue(Class<T> cls, String[] field,ResultSet set) throws SQLException {

        List<T> result=new ArrayList<>();

        //遍历获取结果集
        while (set.next()){
            T t=null;
            try {
                t= cls.getConstructor().newInstance();
            }catch (Exception e){
                throw new RuntimeException("获取无参构造失败,无法实例化对象");
            }

            for(int i=0;i<field.length;i++){
                Field field1 = ReflectUtil.getField(t, field[i]);
                if (field1.getType().isEnum()) {
                    //处理枚举对象
                    ReflectUtil.setValue(field1,t, EnumUtils.getEnum((Class<? extends Enum>) field1.getType(),set.getInt(i+1)));
                }else{
                    ReflectUtil.setValue(field[i],t,set.getObject(i+1));
                }
            }
            result.add(t);
        }
        return result;
    }



    /**
     * @Description:  根据数据源获取sqlUtils类
     * @param dataSource
     * @return org.daiqimeng.example.sqlutil.SqlUtils
     * @author Mr. Dai
     * @date 2023/3/28 15:21
     */
    public static SqlUtils getSqlUtils(DataSource dataSource){
        return new SqlUtils(dataSource);
    }


    /**
     * @Author: Mr. Dai
     * @Description:  获取数据库连接
     * @Date: 21:49 2023/3/28
     **/
    private Connection getConnection(){
        try {
            Connection connection = dataSource.getConnection();
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
