package com.fh.common;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobleHandlerException {

    @ExceptionHandler(MyException.class)
    public ServerResponse HandlerMyException(Exception e){
           e.printStackTrace();
        return ServerResponse.error();
    }

    @ExceptionHandler(Exception.class)
    public ServerResponse HandlerException(Exception e){
        e.printStackTrace();
        return ServerResponse.error();
    }
}
