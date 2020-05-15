package org.zyx.controller;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.AnonymousCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpMethodName;
import com.qcloud.cos.model.GeneratePresignedUrlRequest;
import com.qcloud.cos.region.Region;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.zyx.entity.FileDTO;
import org.zyx.utils.ConnectTencentCloud;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URL;
import java.util.UUID;

/**
 * Created by SunShine on 2020/5/13.
 */
@RestController
public class FileHandler {

    @Autowired
    private ConnectTencentCloud cloud;

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
        String url = cloud.getUrl(fileKey);

        FileDTO fileDTO = new FileDTO();
        fileDTO.setSuccess(1);
        fileDTO.setMessage("图片");
        fileDTO.setUrl(url);

        return fileDTO;

    }







}
