package org.fd.superuser.controller;

import com.system.supercommon.funcbean.R;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.fd.jdbc.SqlUtils;
import org.fd.superuser.user.UserBasePO;
import org.fd.userenum.IdentityEnum;
import org.fd.userenum.StatusEnum;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


/**
 * Description: aaa
 *
 * @author Mr. Dai
 * @date 2023/3/29 11:21
 */
@RestController
@RequestMapping("/test")
@Hidden
@RequiredArgsConstructor
public class TestController {

    private final SqlUtils sqlUtils;

    @GetMapping("/sql")
    @Transactional
    public R test(){

        UserBasePO[] basePOS=new UserBasePO[]{
                (UserBasePO) new UserBasePO()
                        .setUserId(1091819562222671234L)
                        .setUserName("测试")
                        .setUserAccount("ceshi")
                        .setIdentity(IdentityEnum.STAFF)
                        .setStatus(StatusEnum.NORMAL)
                        .setCreateBy(123456L),
                (UserBasePO) new UserBasePO()
                        .setUserId(1091819562222672345L)
                        .setUserName("测试")
                        .setUserAccount("ceshi")
                        .setIdentity(IdentityEnum.STAFF)
                        .setStatus(StatusEnum.NORMAL)
                        .setCreateBy(123456L),
                (UserBasePO) new UserBasePO()
                        .setUserId(1091819562222673456L)
                        .setUserName("测试")
                        .setUserAccount("ceshi")
                        .setIdentity(IdentityEnum.STAFF)
                        .setStatus(StatusEnum.NORMAL)
                        .setCreateBy(123456L),
                (UserBasePO) new UserBasePO()
                        .setUserId(1091819562222674567L)
                        .setUserName("测试")
                        .setUserAccount("ceshi")
                        .setIdentity(IdentityEnum.STAFF)
                        .setStatus(StatusEnum.NORMAL)
                        .setCreateBy(123456L),

        };

        sqlUtils.insertBatch(basePOS);

//
//        int test2 = sqlUtils.delete(new UserBasePO().setUserId(1091819562222678016L)
//                .setUserName("test2"));

        return R.success("");
    }

}
