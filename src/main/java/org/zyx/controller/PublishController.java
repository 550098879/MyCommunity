package org.zyx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.zyx.entity.Question;
import org.zyx.entity.User;
import org.zyx.repository.QuestionRepository;

import javax.servlet.http.HttpServletRequest;

/**处理发布页面请求
 * Created by SunShine on 2020/4/17.
 */
@Controller
public class PublishController {

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/doPublish")
    public String doPublish(Question question, HttpServletRequest request, Model model){

        model.addAttribute("title",question.getTitle());
        model.addAttribute("tags",question.getTags());
        model.addAttribute("discription",question.getDiscription());

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

        question.setCreater_id(user.getId());
        question.setGmt_create(System.currentTimeMillis());
        question.setGmt_modified(System.currentTimeMillis());
        //数据持久化
        if(questionRepository.sendQuestion(question)>0){
            model.addAttribute("success","问题发布成功,回到主页即可看到您的问题哦");
        }else{
            model.addAttribute("error","问题发布失败,请检查问题是否存在少填信息");
        }
        System.out.println(question);


        return "publish";
    }

}
