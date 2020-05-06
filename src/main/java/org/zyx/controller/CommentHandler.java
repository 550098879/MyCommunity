package org.zyx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.zyx.entity.CommentDTO;
import org.zyx.entity.ResultDTO;
import org.zyx.exception.CustomizeErrorCode;
import org.zyx.model.Comment;
import org.zyx.model.User;
import org.zyx.repository.CommentMapper;
import org.zyx.service.CommentService;

import javax.servlet.http.HttpSession;

/**
 * Created by SunShine on 2020/5/4.
 */
@RestController
public class CommentHandler {

    @Autowired
    private CommentService commentService;

    /**
     * 1.使用PostMan插件测试还需要设置Header : contentType application/json
     * 接收的是Json格式的数据的时候需要使用@RequestBody注解
     * <p>
     * 2. ajax发送的异步请求,参数是json格式,但是不需要使用@RequestBody注解
     *
     * @param commentDTO
     * @return
     */
    @PostMapping("/comment")
    public Object comment(@RequestBody CommentDTO commentDTO, HttpSession session) {

        System.out.println("测试" + commentDTO);
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }

        commentService.insertComment(commentDTO, user.getId());

        return ResultDTO.okOf();
    }


}
