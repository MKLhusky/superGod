package com.system.supercommon.util.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Description: spring容器工具类
 * @Author: Mr. Dai
 * @Date: 2023/3/26 13:56
 **/
@Component
public class SpringUtils  implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtils.applicationContext=applicationContext;
    }

    /**
     * @Author: Mr. Dai
     * @Description:  根据bean class 获取容器Bean
     * @Date: 14:01 2023/3/26
     * @param t
     **/
    public static <T> T getBean(Class<T> t){
        return applicationContext.getBean(t);
    }
    /**
     * @Author: Mr. Dai
     * @Description:  根据bean name 获取容器bean
     * @Date: 14:01 2023/3/26
     * @param name
     **/
    public static <T> T getBean(String name){
        return (T) applicationContext.getBean(name);
    }

    /**
     * @Author: Mr. Dai
     * @Description:  自己玩
     * @Date: 14:02 2023/3/26
     **/
    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }
}
