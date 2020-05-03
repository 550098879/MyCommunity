package org.zyx.exception;

/** 枚举类
 *  1.定义不同类型的ErrorCode
 *  2.由于存在不同类型的错误信息,如用户,问题,系统等错误信息,会定义多种错误枚举类
 *  3.枚举类定义的不是常量,而是对象,
 *  4.私有构造实现无法在其他类创建对象,只能在本类创建对象
 * Created by SunShine on 2020/5/3.
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode{

    /** 1.定义的对象,调用枚举类的私有方法,给message赋值
     *  2.QUESTION_NOT_FOUND 不是常量,而是对象
     *  3.message是需要的错误信息
     *  4.执行顺序: 调用私有构造 -- 为message赋值 -- 在异常类中调用getMessage()方法获取错误信息
     */
    QUESTION_NOT_FOUND("问题已删除或不存在,请重试");

    private String message;

    CustomizeErrorCode(String message) {
        this.message = message;
    }

    @Override  //重写接口的方法
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message;
    }
}
