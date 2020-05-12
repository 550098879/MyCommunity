package org.zyx.enums;

/**
 * Created by SunShine on 2020/5/11.
 */
public enum InformStatusEnum {

    UNREAD(0),
    READ(1),
    ;


    int status;

    InformStatusEnum(int status){
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
