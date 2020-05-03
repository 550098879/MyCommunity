package org.zyx.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.zyx.exception.CustomizeException;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Handler;

/** 异常处理
 * Created by SunShine on 2020/5/3.
 */

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(Exception.class)
    ModelAndView handle(HttpServletRequest request, Throwable ex) {

        HttpStatus status = getStatus(request);//错误状态

        ModelAndView modelAndView = new ModelAndView("error");

        if(ex instanceof CustomizeException){
            modelAndView.addObject("errorMsg",ex.getMessage());
        }else{
            modelAndView.addObject("errorMsg","太热了,服务器一边凉快去了!");
        }


        return modelAndView;
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }


}
