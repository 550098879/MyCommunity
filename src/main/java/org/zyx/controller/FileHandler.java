package org.zyx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.zyx.entity.FileDTO;
import org.zyx.utils.ConnectTencentCloud;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * Created by SunShine on 2020/5/13.
 */
@Controller
public class FileHandler {

    @Autowired
    private ConnectTencentCloud cloud;

    @ResponseBody
    @RequestMapping("/uploadImage")
    public Object uploadImage(HttpServletRequest request) throws IOException {

        //获取上传的文件
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile multipartFile= multipartRequest.getFile("editormd-image-file");
        InputStream inputStream = multipartFile.getInputStream();
        String fileKey = UUID.randomUUID().toString()+multipartFile.getOriginalFilename();
        System.out.println(fileKey);
        //执行文件上传云服务器
        cloud.uploadStream(inputStream,fileKey,multipartFile.getSize());

        //将文件存储至服务器,用于图片预览
        //String url = cloud.getUrl(fileKey);

        FileDTO fileDTO = new FileDTO();
        fileDTO.setSuccess(1);
        fileDTO.setMessage("图片");
        fileDTO.setUrl("/getImage/"+fileKey);

        return fileDTO;

    }

    //将图片请求重定向到服务器的访问路径
    @RequestMapping("/getImage/{fileKey}")
    public String getImage (@PathVariable("fileKey") String fileKey){

        String url = cloud.getUrl(fileKey);

        return "redirect:"+url;
    }









}
