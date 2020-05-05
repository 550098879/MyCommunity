package org.zyx.exception;

/** 自定义异常类
 * Created by SunShine on 2020/5/3.
 */
public class CustomizeException extends RuntimeException{

    private String massage;
    private Integer code;

    /** 1.顶级接口参数
     *  2.实质上是不同的枚举类的对象(实现了ICustomizeErrorCode错误码接口)
     *  3.调用枚举类对象的getMessage()方法,获取错误信息
     */
    public CustomizeException(ICustomizeErrorCode errorCode){
        this.massage = errorCode.getMessage();
        this.code = errorCode.getCode();
    }

    public String getMessage() {
        return massage;
    }

    public Integer getCode() {
        return code;
    }
}
