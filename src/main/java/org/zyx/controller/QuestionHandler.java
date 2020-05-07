package org.zyx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zyx.entity.CommentDTO;
import org.zyx.entity.QuestionDTO;
import org.zyx.model.Comment;
import org.zyx.repository.QuestionExtMapper;
import org.zyx.service.QuestionService;

import java.util.Collection;
import java.util.List;

/**
 * Created by SunShine on 2020/4/29.
 */
@Controller
public class QuestionHandler {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuestionExtMapper questionExtMapper;

    @GetMapping("/question/{id}")
    public String question(@PathVariable("id") long id, Model model){

        QuestionDTO question = questionService.getById(id);
//        累加阅读数
        questionExtMapper.incView(id);
        model.addAttribute("question",question);
        return "questionInfo";
    }




}
