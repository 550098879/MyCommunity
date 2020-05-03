package org.zyx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.zyx.entity.QuestionModel;
import org.zyx.model.User;
import org.zyx.service.QuestionService;

import javax.servlet.http.HttpServletRequest;

/**处理发布页面请求
 * Created by SunShine on 2020/4/17.
 */
@Controller
public class PublishController {

    @Autowired
    private QuestionService questionService;


    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }


    @GetMapping("/publish/{id}")
    public String publish(@PathVariable("id") int id,Model model){
        QuestionModel question = questionService.getById(id);
        model.addAttribute("title",question.getTitle());
        model.addAttribute("discription",question.getDiscription());
        model.addAttribute("tags",question.getTags());
        model.addAttribute("id",id);

        return "publish";
    }

    @PostMapping("/doPublish")
    public String doPublish(QuestionModel question, HttpServletRequest request, Model model){

        model.addAttribute("title",question.getTitle());
        model.addAttribute("discription",question.getDiscription());
        model.addAttribute("tags",question.getTags());
        model.addAttribute("id",question.getId());

        User user= (User) request.getSession().getAttribute("user");

        if(user==null){
            model.addAttribute("error","用户未登陆,请登陆后再发布问题");
            return "publish";
        }

        if("".equals(question.getTitle().trim()) || question.getTitle()==null){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if("".equals(question.getDiscription().trim()) || question.getDiscription()==null){
            model.addAttribute("error","问题描述不够清晰");
            return "publish";
        }
        if("".equals(question.getTags().trim()) || question.getTags()==null){
            model.addAttribute("error","至少要选择一个标签哦");
            return "publish";
        }

        question.setCreaterId((long)user.getId());

        //数据持久化
        if(questionService.createOrUpdate(question)>0){
            model.addAttribute("success","问题发布成功,回到主页即可看到您的问题哦");
        }else{
            model.addAttribute("success","问题更新成功");
        }

        return "publish";
    }



}
