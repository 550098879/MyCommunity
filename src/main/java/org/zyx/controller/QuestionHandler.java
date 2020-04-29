package org.zyx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.zyx.entity.Question;
import org.zyx.service.QuestionService;

/**
 * Created by SunShine on 2020/4/29.
 */
@Controller
public class QuestionHandler {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable("id") int id, Model model){

        Question question = questionService.getById(id);
        model.addAttribute("question",question);
        return "questionInfo";
    }

}
