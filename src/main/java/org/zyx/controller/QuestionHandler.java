package org.zyx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zyx.entity.CommentDTO;
import org.zyx.entity.QuestionDTO;
import org.zyx.enums.InformStatusEnum;
import org.zyx.model.*;
import org.zyx.repository.InformMapper;
import org.zyx.repository.QuestionExtMapper;
import org.zyx.service.QuestionService;

import javax.servlet.http.HttpSession;
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
    @Autowired
    private InformMapper informMapper;

    @GetMapping("/question/{id}")
    public String question(@PathVariable("id") long id, Model model){

        QuestionDTO questionDTO = questionService.getById(id);

        Collection<Question> aboutQuestion = questionService.selectRelated(questionDTO);

        model.addAttribute("aboutQuestion",aboutQuestion);

//        累加阅读数
        model.addAttribute("question",questionDTO);
        questionExtMapper.incView(id);

        return "questionInfo";
    }

    //处理消息为已读
    @GetMapping("/inform/{id}/{informId}")
    public String informtoQuestion(@PathVariable("id") long id, @PathVariable("informId") long informId,Model model){

        //将通知设为已读
        Inform inform = new Inform();
        inform.setStatus(InformStatusEnum.READ.getStatus());
        InformExample example = new InformExample();
        example.createCriteria().andIdEqualTo(informId);
        informMapper.updateByExampleSelective(inform, example);//更新状态
        return "redirect:/question/"+id;
    }



}
