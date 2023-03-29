package org.fd.jdbc;

import com.system.supercommon.util.StringUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: sql 语句组装
 * @Author: Mr. Dai
 * @Date: 2023/3/28 20:14
 **/
public class SqlAssembly {

    /**
     * @Author: Mr. Dai
     * @Description:  组装sql
     * @Date: 20:20 2023/3/28
     * @param tableName
     * @param field
     * @param status
     * @param condition
     **/
    protected static String assembly(String tableName,String[] field,SqlStatus status,SqlCondition... condition){
        String sql=switch (status){
            case QUERY ->  selectSql(tableName,field,condition);
            case DELETE -> deleteSql(tableName,condition);
            case INSERT -> insertSql(tableName,field);
            case UPDATE -> updateSql(tableName,field,condition);
            default -> null;
        };
        return sql;
    }

    /**
     * @Author: Mr. Dai
     * @Description:  组装更新语句 可以预编译
     * @Date: 20:54 2023/3/28
     * @param tableName
     * @param field
     * @param sqlCondition
     **/
    private  static String updateSql(String tableName,String[] field,SqlCondition... sqlCondition){
        StringBuffer buffer=new StringBuffer(String.format("update %s set ",tableName));
        List<String> temp=new ArrayList<>();
        for (String s : field) {
            temp.add(String.format("%s = ?",StringUtils.toSnake(s)));
        }
        buffer.append(temp.stream().collect(Collectors.joining(",")));
        assemblyCondition(buffer,true,sqlCondition);
        return buffer.toString();
    }

    /**
     * @Author: Mr. Dai
     * @Description:  组装insert sql 可以预编译
     * @Date: 20:51 2023/3/28
     * @param tableName
     * @param field
     **/
    private static String insertSql(String tableName, String[] field){
        //预编译
        List<String> temp=new ArrayList<>();
        //字段名称
        List<String> tempField=new ArrayList<>();
        for (String s : field) {
            temp.add("?");
            tempField.add(String.format("`%s`",StringUtils.toSnake(s)));
        }
        StringBuffer buffer=new StringBuffer("insert into ");
        buffer.append(tableName);
        buffer.append(" ( ");
        buffer.append(tempField.stream().collect(Collectors.joining(",")));
        buffer.append(" ) ");
        buffer.append(" values ( ");
        buffer.append(temp.stream().collect(Collectors.joining(",")));
        buffer.append(" ) ");
        return buffer.toString();
    }

    /**
     * @Author: Mr. Dai
     * @Description:  查询sql组装
     * @Date: 20:20 2023/3/28
     * @param tableName
     * @param field
     * @param conditions
     **/
    private static String selectSql(String tableName,String[] field,SqlCondition... conditions){
        List<String> solid=new ArrayList<>();
        for(int i=0;i<field.length;i++){
            solid.add(String.format("`%s`", StringUtils.toSnake(field[i])));
        }
        StringBuffer buffer=new StringBuffer("select ");
        buffer.append(solid.stream().collect(Collectors.joining(",")));
        buffer.append(" from ");
        buffer.append(tableName);
        buffer.append(" ");
        assemblyCondition(buffer,false,conditions);

        return buffer.toString();
    }

    /**
     * @Author: Mr. Dai
     * @Description: 删除sql组装
     * @Date: 20:37 2023/3/28
     * @param tableName
     * @param conditions
     **/
    private static String deleteSql(String tableName, SqlCondition... conditions){
        StringBuffer buffer=new StringBuffer("delete from ");
        buffer.append(tableName);
        assemblyCondition(buffer,false,conditions);
        return buffer.toString();
    }

    /**
     * @Author: Mr. Dai
     * @Description: 组装条件
     * @Date: 20:35 2023/3/28
     * @param buffer
     * @param flag 是否开启预编译 true 开启 false 不开启
     * @param conditions
     **/
    protected static void assemblyCondition(StringBuffer buffer,boolean flag,SqlCondition... conditions){
        if(null!=conditions&&conditions.length>0){
            buffer.append(" where ");
            List<String> temp=new ArrayList<>();
            for (SqlCondition condition : conditions) {
                temp.add(String.format("%s = %s",StringUtils.toSnake(condition.getName()),flag?"?":condition.getValue()));
            }
            buffer.append(temp.stream().collect(Collectors.joining(" and ")));
        }
    }
}
