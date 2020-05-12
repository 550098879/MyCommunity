package org.zyx.enums;

/**
 * Created by SunShine on 2020/5/11.
 */
public enum InformEnum {

    REPLY_QUESTION(1,"回复了问题"),
    REPLY_COMMENT(2,"回复了评论"),

    ;

    private int type;
    private String name;

    InformEnum(int type,String name){
        this.type = type;
        this.name = name;//用于展示
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
