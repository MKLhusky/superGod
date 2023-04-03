package com.system.supersystem.example.ExceptionExample;

import com.system.supercommon.handler.login.TransRequestContextHolder;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

//@Slf4j
//@RestController
//@RequestMapping("/exception")
public class ExceptionController {



    @RequestMapping("/loginExcetion")
    public void loginExcetion(){
        ThreadLocal<TransRequestContextHolder.UserInfo> userInfo = TransRequestContextHolder.getUserInfo();

        if (userInfo.get() == null){
            throw new LoginException("未获取token");
        }
    }

    @ResponseStatus(value = HttpStatus.CONFLICT, reason = "Data integrity violation")
    @ExceptionHandler(DataIntegrityViolationException.class)
    @RequestMapping("/conflict")
    public void conflict(){

    }

    // Specify name of a specific view that will be used to display the error:
    @ExceptionHandler({SQLException.class, DataAccessException.class})
    public String databaseError() {
        //跳转到指定的视图页面，名字带databaseError的前端文件
        return "databaseError";
    }


    // Total control - setup a model and return the view name yourself. Or
    // consider subclassing ExceptionHandlerExceptionResolver (see below).
    @ExceptionHandler(Exception.class)
    @RequestMapping("/handleError")
    public ModelAndView handleError(HttpServletRequest req, Exception ex) {
        //log.error("Request: " + req.getRequestURL() + " raised " + ex);

        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("error");
        return mav;
    }
}
