package org.zyx.enums;

/**
 * Created by SunShine on 2020/5/4.
 */
public enum CommentTypeEnum {

    QUESTION(1),
    COMMENT(2)
    ;

    private Integer type;

    CommentTypeEnum(Integer type){
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public static boolean isExist(Integer type) {
        //不能使用this,是类方法,使用类名引用
        //values() 遍历枚举类所有对象的方法
        for (CommentTypeEnum commentTypeEnum: CommentTypeEnum.values()) {
            if(commentTypeEnum.getType() == type){
                return true;
            }
        }
        return false;
    }
}
