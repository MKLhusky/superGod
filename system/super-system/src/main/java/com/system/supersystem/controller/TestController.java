package com.system.supersystem.controller;

import com.system.supercommon.result.R;
import com.system.supercommon.util.SnowFlake;
import com.system.supersystem.dao.TestMapper;
import com.system.supersystem.po.Test;
import jakarta.annotation.Resource;
import org.fd.util.SqlUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: Mr. Dai
 * @Date: 2023/3/26 15:02
 **/
@RequestMapping("/test")
@RestController
public class TestController {

    @Resource
    private TestMapper testMapper;

    @GetMapping("/mybatis")
    public R testMybatis(){

        Test test = new Test();
        test.setId(SnowFlake.getId());
        test.setNickName("张三");
        test.setAge(18);

        List<Test> list=new ArrayList<>();
        list.add(test);

        SqlUtils.execute(list, TestMapper.class,(t,m)->m.insert(t));


        return R.success(testMapper.selectById(1089626834785202176L));
    }
}
