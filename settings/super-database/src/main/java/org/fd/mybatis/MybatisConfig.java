package org.fd.mybatis;

import org.apache.ibatis.session.SqlSessionFactory;
import org.fd.jdbc.SqlUtils;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
public class MybatisConfig {


    @Autowired
    private Environment env;


    /**
     * @Author: Mr. Dai
     * @Description:  事务管理器 交由springboot管理
     * @Date: 13:50 2023/3/26
     * @param dataSource
     **/
    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

//    /**
//     * @Author: Mr. Dai
//     * @Description:  sqlSession模板
//     * @Date: 18:18 2023/3/26
//     * @param sessionFactory
//     **/
//    @Bean
//    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sessionFactory){
//        return new SqlSessionTemplate(sessionFactory);
//    }

    /**
     * @Author: Mr. Dai
     * @Description:  项目公共简单sql执行工具
     * @Date: 21:04 2023/3/28
     * @param dataSource
     **/
    @Bean
    public SqlUtils sqlUtils(DataSource dataSource){
        return SqlUtils.getSqlUtils(dataSource);
    }

    /**
     * @Author: Mr. Dai
     * @Description:  sqlSession工厂
     * @Date: 18:18 2023/3/26
     * @param dataSource
     **/
    @Bean
    public SqlSessionFactory sessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

        //配置mapper扫描路径
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(getMybatisProperty("mybatis.mapper-locations")));

        //TODO   权宜之计  配置文件增加一个这里就需要增加一个
        //mybatis 配置
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(Boolean.valueOf(getMybatisProperty("mybatis.configuration.map-underscore-to-camel-case")));
        configuration.setDefaultEnumTypeHandler(MybatisEnumHandler.class);
        sqlSessionFactoryBean.setConfiguration(configuration);

        return sqlSessionFactoryBean.getObject();
    }


    private String getMybatisProperty(String key){
        return env.getProperty(key);
    }

}
