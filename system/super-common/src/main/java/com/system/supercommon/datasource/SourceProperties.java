package com.system.supercommon.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.sql.DataSource;

@ConfigurationProperties("spring.datasource.druid")
public class SourceProperties {
    private int initialSize=5;

    private int minIdle=5;

    private int maxActive=20;

    private int maxWait=1000*60;

    private int timeBetweenEvictionRunsMillis=1000*60*60;

    private int minEvictableIdleTimeMillis=1000*60*30;

    private int maxEvictableIdleTimeMillis=1000*60*60;

    private String validationQuery="SELECT 1 FROM DUAL";

    private boolean testWhileIdle=true;

    private boolean testOnBorrow=false;

    private boolean testOnReturn=false;

    public int getMaxActive(){
        return this.maxActive;
    }


    public SourceProperties setInitialSize(int initialSize) {
        this.initialSize = initialSize;
        return this;
    }

    public SourceProperties setMinIdle(int minIdle) {
        this.minIdle = minIdle;
        return this;
    }

    public SourceProperties setMaxActive(int maxActive) {
        this.maxActive = maxActive;
        return this;
    }

    public SourceProperties setMaxWait(int maxWait) {
        this.maxWait = maxWait;
        return this;
    }

    public SourceProperties setTimeBetweenEvictionRunsMillis(int timeBetweenEvictionRunsMillis) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
        return this;
    }

    public SourceProperties setMinEvictableIdleTimeMillis(int minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
        return this;
    }

    public SourceProperties setMaxEvictableIdleTimeMillis(int maxEvictableIdleTimeMillis) {
        this.maxEvictableIdleTimeMillis = maxEvictableIdleTimeMillis;
        return this;
    }

    public SourceProperties setValidationQuery(String validationQuery) {
        this.validationQuery = validationQuery;
        return this;
    }

    public SourceProperties setTestWhileIdle(boolean testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
        return this;
    }

    public SourceProperties setTestOnBorrow(boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
        return this;
    }

    public SourceProperties setTestOnReturn(boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
        return this;
    }

    public DataSource setProperties(DruidDataSource dataSource){

        //初始化连接数
        dataSource.setInitialSize(this.initialSize);
        //最大连接数
        dataSource.setMaxActive(this.maxActive);
        //最小连接数
        dataSource.setMinIdle(this.minIdle);
        //获取连接等待时间
        dataSource.setMaxWait(this.maxWait);
        //检测回收连接时间
        dataSource.setTimeBetweenEvictionRunsMillis(this.timeBetweenEvictionRunsMillis);
        //连接最小生存时间
        dataSource.setMinEvictableIdleTimeMillis(this.minEvictableIdleTimeMillis);
        //连接最大生存时间
        dataSource.setMaxEvictableIdleTimeMillis(this.maxEvictableIdleTimeMillis);
        //检测连接是否有效语句
        dataSource.setValidationQuery(this.validationQuery);

        //连接大于生存时间 是否检测
        dataSource.setTestWhileIdle(this.testWhileIdle);
        //申请连接检测是否有效
        dataSource.setTestOnBorrow(this.testOnBorrow);

        //释放连接检测是否有效
        dataSource.setTestOnReturn(this.testOnReturn);

        return dataSource;
    }
}
