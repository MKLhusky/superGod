package com.system.supersystem.dao;

import com.system.supersystem.po.Test;

/**
 * @Description: 测试mybatis
 * @Author: Mr. Dai
 * @Date: 2023/3/26 14:11
 **/

public interface TestMapper {

    int insert(Test test);

    Test selectById(Long id);
}
