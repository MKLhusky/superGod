package org.fd.superuser.controller;

import com.system.supercommon.funcbean.R;
import jakarta.annotation.Resource;
import org.fd.pojo.dto.UserLoginDTO;
import org.fd.superuser.service.UserService;
import org.fd.superuser.serviceimpl.UserServiceImpl;
import org.springframework.validation.annotation.Validated;
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

    @Resource(type = UserServiceImpl.class)
    private UserService userService;


    /**
     * @Description:   根据账号密码验证 成功返回token
     * @param userLoginDto
     * @return com.system.supercommon.result.R
     * @author Mr. Dai
     * @date 2023/3/29 17:47
     */
    @PostMapping("/loginPassword")
    public R  getUser(@RequestBody @Validated UserLoginDTO userLoginDto){
        return R.success(userService.getUser(userLoginDto));
    }


    /**
     * @Description:   根据用户id 获取用户信息详情
     * @param userId
     * @return com.system.supercommon.result.R
     * @author Mr. Dai
     * @date 2023/3/30 15:10
     */
    @GetMapping("selectUserInfo/{userId}")
    public R selectUserInfo(@PathVariable("userId")Long userId){
        return R.success(userService.selectUserInfo(userId));
    }
}
