package org.zyx.entity;

import lombok.Data;
import org.zyx.exception.CustomizeErrorCode;
import org.zyx.exception.CustomizeException;

/**
 * Created by SunShine on 2020/5/4.
 */
@Data
public class ResultDTO {

    private Integer code;
    private String message;

    public static ResultDTO errorOf(Integer code,String message){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;
    }

    public static Object errorOf(CustomizeErrorCode noLogin) {
        return errorOf(noLogin.getCode(),noLogin.getMessage());
    }

    public static Object errorOf(CustomizeException ex) {
        return errorOf(ex.getCode(),ex.getMessage());
    }


    public static ResultDTO okOf(){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("评论成功");
        return resultDTO;
    }


}
