package com.lv.example.springboot.exception;


import com.lv.example.springboot.entity.R;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthException.class)
    public R handleAuthException(AuthException e)
    {
        R r = new R();
        r.put("code",e.getCode());
        r.put("msg",e.getMessage());
        return r;

    }
}
