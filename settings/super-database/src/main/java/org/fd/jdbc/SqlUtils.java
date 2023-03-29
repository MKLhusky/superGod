package org.fd.jdbc;

import com.system.supercommon.util.EnumUtils;
import com.system.supercommon.util.ReflectUtil;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
     * @Description: 根据条件判断数据是否存在
     * @param t
     * @return boolean
     * @author Mr. Dai
     * @date 2023/3/29 15:29
     */
    public <T> boolean isExist(T t){
        List<SqlCondition> condition = SqlBeanUtils.getCondition(t);
        String table = SqlBeanUtils.getTable(t.getClass(), null);
        StringBuffer buffer=new StringBuffer(String.format("select 1 from %s ",table));
        SqlAssembly.assemblyCondition(buffer,false,condition.toArray(new SqlCondition[0]));
        buffer.append(" limit 1");

        if (log.isInfoEnabled()) {
            log.info(buffer.toString());
        }
        ResultSet resultSet = null;
        try {
            resultSet = getConnection().createStatement().executeQuery(buffer.toString());
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
    public <T>T selectOne(T t){
        return selectOne(t,new QueryFiledExclude<T>());
    }


    /**
     * @Author: Mr. Dai
     * @Description: 根据条件和字段处理器 获取单挑数据
     * @Date: 21:07 2023/3/28
     * @param t
     * @param handlerField
     **/
    public <T>T selectOne(T t,HandlerField<T,Object> handlerField){

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
    public <T> List<T> selectList(T t){
        return selectList(t,new QueryFiledExclude<T>());
    }


    /**
     * @Author: Mr. Dai
     * @Description:  根据条件和字段处理器查询集合
     * @Date: 22:18 2023/3/28
     * @param t
     * @param handlerField
     **/
    public <T> List<T> selectList(T t,HandlerField<T,Object> handlerField){
        //获取所有查询字段
        Set<String> fields = SqlBeanUtils.getFields(t, handlerField);
        String[] arrFields = fields.toArray(new String[0]);
        //构造条件
        List<SqlCondition> condition = SqlBeanUtils.getCondition(t);

        //拼装sql
        String sql = SqlAssembly.assembly(SqlBeanUtils.getTable(t.getClass(), null), arrFields, SqlStatus.QUERY, condition.toArray(new SqlCondition[0]));

        if (log.isInfoEnabled()) {
            log.info(sql);
        }

        Connection connection = getConnection();
        try {
            //执行sql
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

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
