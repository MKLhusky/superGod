package org.fd.superuser.controller;

import com.system.supercommon.funcbean.R;
import com.system.supercommon.util.SnowFlake;
import jakarta.annotation.Resource;
import org.fd.jdbc.FieldSelect;
import org.fd.jdbc.SqlUtils;
import org.fd.superuser.po.UserBasePO;
import org.fd.userenum.IdentityEnum;
import org.fd.userenum.SexEnum;
import org.fd.userenum.StatusEnum;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Description: aaa
 *
 * @author Mr. Dai
 * @date 2023/3/29 11:21
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private SqlUtils sqlUtils;

    @GetMapping("/sql")
    public R test(){

        return R.success(sqlUtils.delete(new UserBasePO().setUserId(1091819562222678016L)
                .setUserName("test2")));
    }

}
