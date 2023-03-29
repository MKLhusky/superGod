package org.fd.superuser.controller;

import com.system.supercommon.result.R;
import jakarta.annotation.Resource;
import org.fd.pojo.dto.UserLoginDto;
import org.fd.superuser.service.UserService;
import org.springframework.web.bind.annotation.*;

/**
 * Description: 用户controller
 *
 * @author Mr. Dai
 * @date 2023/3/29 17:11
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;


    /**
     * @Description:   根据账号密码验证 成功返回token
     * @param userLoginDto
     * @return com.system.supercommon.result.R
     * @author Mr. Dai
     * @date 2023/3/29 17:47
     */
    @PostMapping("/login")
    public R  getUser(@RequestBody UserLoginDto userLoginDto){
        return R.success(userService.getUser(userLoginDto));
    }
}
