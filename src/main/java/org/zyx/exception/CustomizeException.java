package org.zyx.exception;

/** 自定义异常类
 * Created by SunShine on 2020/5/3.
 */
public class CustomizeException extends RuntimeException{

    private String massage;
    public CustomizeException(String msg){
        this.massage=msg;
    }

    public CustomizeException(ICustomizeErrorCode errorCode){

        /** 1.顶级接口参数
         *  2.实质上是不同的枚举类的对象(实现了ICustomizeErrorCode错误码接口)
         *  3.调用枚举类对象的getMessage()方法,获取错误信息
         */
        this.massage=errorCode.getMessage();
    }

    public String getMessage() {
        return massage;
    }
}
