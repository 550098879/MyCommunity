package org.zyx.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.zyx.exception.CustomizeException;

import javax.servlet.http.HttpServletRequest;

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
            /** 判断错误是否可以被自定义类处理,可以则显示自定义错误信息
             *  此处调用错误类的getMessage()获取错误信息
             */
            modelAndView.addObject("message",ex.getMessage());
        }else{
            modelAndView.addObject("message","太热了,服务器一边凉快去了!");
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
