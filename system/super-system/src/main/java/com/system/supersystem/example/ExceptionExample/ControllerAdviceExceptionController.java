package com.system.supersystem.example.ExceptionExample;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

/*@Controller
@ControllerAdvice
@RequestMapping("/controllerAdvice")*/
public class ControllerAdviceExceptionController {

    /*@ExceptionHandler(Exception.class)
    @RequestMapping("/conflict")*/
    public void conflict(){
        System.out.println("1");
    }


}
