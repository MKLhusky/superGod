package org.fd.superuser.controller;

import com.system.supercommon.bean.ParentPO;
import com.system.supercommon.result.R;
import org.fd.jdbc.QueryFieldSelect;
import org.fd.jdbc.QueryFiledExclude;
import org.fd.jdbc.SqlUtils;
import org.fd.superuser.po.UserBasePO;
import org.fd.superuser.userenum.IdentityEnum;
import org.fd.superuser.userenum.SexEnum;
import org.fd.superuser.userenum.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * Description: aaa
 *
 * @author Mr. Dai
 * @date 2023/3/29 11:21
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private SqlUtils sqlUtils;


}
