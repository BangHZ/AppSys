package com.ybw.appsys;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
        return "error/error_500";
    }
}
