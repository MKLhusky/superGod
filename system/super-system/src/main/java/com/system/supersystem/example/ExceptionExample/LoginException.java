package com.system.supersystem.example.ExceptionExample;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "登录失败，token未获取")
public class LoginException extends RuntimeException{

    public LoginException(String msg){
        super(msg);
    }
}
