package com.system.supersystem;

import com.system.supersystem.example.ExceptionExample.ExceptionController;
import com.system.supersystem.login.controller.LoginController;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SuperSystemApplicationTests {

    @Autowired
    private ExceptionController exceptionController;


    @Test
    void LoginExceptionTest() {
        exceptionController.loginExcetion();
    }

}
