package org.fd.superuser.controller;

import com.system.supercommon.result.R;
import com.system.supercommon.util.SnowFlake;
import jakarta.annotation.Resource;
import org.fd.jdbc.SqlUtils;
import org.fd.superuser.dao.UserDao;
import org.fd.superuser.po.UserBasePO;
import org.fd.superuser.service.UserService;
import org.fd.superuser.userenum.IdentityEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

}
