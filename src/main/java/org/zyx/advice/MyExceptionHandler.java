package org.zyx.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.zyx.entity.ResultDTO;
import org.zyx.exception.CustomizeErrorCode;
import org.zyx.exception.CustomizeException;

import javax.servlet.http.HttpServletRequest;

/** 异常处理
 * Created by SunShine on 2020/5/3.
 */
@RestController
@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(Exception.class)
    Object handle(HttpServletRequest request, Throwable ex) {

        String contentType = request.getContentType();
        ModelAndView modelAndView = new ModelAndView("error");
        if ("application/json".equals(contentType)){
            //返回json
            if(ex instanceof CustomizeException){
                return ResultDTO.errorOf((CustomizeException)ex);
            }else{
                return ResultDTO.errorOf(CustomizeErrorCode.SYS_ERROR);
            }
        }else{
            //错误页面跳转
            if(ex instanceof CustomizeException){
                /** 判断错误是否可以被自定义类处理,可以则显示自定义错误信息
                 *  此处调用错误类的getMessage()获取错误信息
                 */
                modelAndView.addObject("message",ex.getMessage());
                return modelAndView;
            }else{

                if (ex.getMessage().indexOf("1048576")>-1){
                    return ResultDTO.errorOf(CustomizeErrorCode.File_exceed);
                }

                return ResultDTO.errorOf(CustomizeErrorCode.SYS_ERROR);
            }

        }


    }




}
