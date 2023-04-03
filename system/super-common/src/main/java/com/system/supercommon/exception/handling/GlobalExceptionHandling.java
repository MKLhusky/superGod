package com.system.supercommon.exception.handling;


import com.system.supercommon.exception.base.SuperBaseException;
import com.system.supercommon.funcbean.R;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandling {


    @ExceptionHandler(SuperBaseException.class)
    public ResponseEntity<R> baseException(SuperBaseException e){
        return new ResponseEntity<R>(R.fail(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
