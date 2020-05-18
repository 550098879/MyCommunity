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
    QUESTION_NOT_FOUND(2001,"问题已删除或不存在,请重试"),
    TARGET_PARAM_NOT_FOUND(2002,"未选中任何问题或评论进行回复"),
    NO_LOGIN(2003,"未登录不能进行评论,请先登录后操作"),
    SYS_ERROR(2004,"太热了,服务器一边凉快去了!"),
    TYPE_PARAM_WRONG(2005,"评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2006,"回复的评论不存在了,要不换个试试"),
    CONTENT_IS_EMPTY(2007,"回复的内容不能为空"),
    File_exceed(2008,"上传失败,图片大小不能超过1048576个字节(1mb)!"),
    ;

    private String message;
    private Integer code;

//    私有构造方法
    CustomizeErrorCode(Integer code,String message) {
        this.message = message;
        this.code = code;
    }

    //重写接口的方法
    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }



}
