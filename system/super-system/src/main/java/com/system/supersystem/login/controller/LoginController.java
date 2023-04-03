package com.system.supersystem.login.controller;


import com.system.supercommon.exception.base.SuperBaseException;
import com.system.supercommon.funcbean.R;
import com.system.supercommon.util.http.HttpRequest;
import lombok.extern.slf4j.Slf4j;
import org.fd.pojo.dto.UserLoginDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: Mr. Dai
 * @Date: 2023/3/26 15:02
 **/
@Slf4j
@RequestMapping("/login")
@RestController
public class LoginController {

    @RequestMapping
    public R login(@RequestBody UserLoginDTO user){

        R response = HttpRequest.post("http://localhost:9000/super_user/user/loginPassword").setParams(user).connection();

        System.out.println(response);
        return response;
    }

    @RequestMapping("/test")
    public R test(){
        if (1 == 1) {
            throw new SuperBaseException("报错了");
        }
        return R.success();
    }

}
