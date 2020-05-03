package org.zyx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zyx.entity.PagingData;
import org.zyx.model.User;
import org.zyx.service.QuestionService;

import javax.servlet.http.HttpSession;

/**
 * Created by SunShine on 2020/4/29.
 */
@Controller
@RequestMapping("/profile")
public class ProfileHandler {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/{action}")
    public String profile(@PathVariable("action") String action, Model model, HttpSession session,
                          @RequestParam(name="currentPage", required=false, defaultValue="1") Integer currentPage,
                          @RequestParam(name="count", required=false, defaultValue="5") Integer count){
        //检测是否登陆
        User user= (User) session.getAttribute("user");
        if(user == null){
            return "redirect:/";
        }

        if("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的问题");
//            获取问题
            PagingData myQuestion = questionService.findMyQuestion(user.getId(), (currentPage-1)*count, count);
            session.setAttribute("myQuestion",myQuestion);

        }else if("reply".equals(action)){
            model.addAttribute("section","reply");
            model.addAttribute("sectionName","最新回复");
        }

        return action;
    }





}
