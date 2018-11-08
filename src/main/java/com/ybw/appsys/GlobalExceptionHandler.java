package com.ybw.appsys;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;

/**
 * @author ybw
 * @createDate 2018/11/2
 *  异常统一拦截
 **/
@ControllerAdvice
public class GlobalExceptionHandler  {

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, HttpServletResponse response){
//        System.out.println(response.getStatus());
        return "403";
    }
}
