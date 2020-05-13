package org.zyx.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zyx.entity.FileDTO;

/**
 * Created by SunShine on 2020/5/13.
 */
@RestController
public class FileHandler {


    @RequestMapping("/uploadImage")
    public Object uploadImage(){
        FileDTO fileDTO = new FileDTO();

        fileDTO.setSuccess(1);
        fileDTO.setMessage("图片");

        fileDTO.setUrl("/images/刈剑.png");

        return fileDTO;
    }



}
