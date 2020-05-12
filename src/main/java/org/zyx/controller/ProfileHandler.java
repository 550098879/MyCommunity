package org.zyx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.zyx.entity.PagingData;
import org.zyx.enums.InformStatusEnum;
import org.zyx.model.InformExample;
import org.zyx.model.QuestionExample;
import org.zyx.model.User;
import org.zyx.repository.InformMapper;
import org.zyx.repository.QuestionMapper;
import org.zyx.service.InformService;
import org.zyx.service.QuestionService;

import javax.servlet.http.HttpSession;

/**
 * Created by SunShine on 2020/4/29.
 */
@Controller
@RequestMapping("/profile")
public class ProfileHandler {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private InformService informService;
    @Autowired
    private InformMapper informMapper;


    @GetMapping("/{action}")
    public String profile(@PathVariable("action") String action, Model model, HttpSession session,
                          @RequestParam(name="currentPage", required=false, defaultValue="1") Integer currentPage,
                          @RequestParam(name="count", required=false, defaultValue="4") Integer count){
        //检测是否登陆
        User user= (User) session.getAttribute("user");
        if(user == null){
            return "redirect:/";
        }
        if("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的问题");
//            获取问题
            PagingData myQuestion = questionService.findMyQuestion(user.getId(), currentPage, count);
            session.setAttribute("myQuestion",myQuestion);
            session.setAttribute("totleCount",questionMapper.countByExample(new QuestionExample()));

        }else if("reply".equals(action)){
            model.addAttribute("section","reply");
            model.addAttribute("sectionName","最新回复");

            //获取最新回复
            PagingData pagingData = informService.list(user.getId(), currentPage, 10);
            session.setAttribute("pagingData",pagingData);

        }
        return action;
    }



    @ResponseBody
    @GetMapping("/getInformCount")
    public Long getInformCount(HttpSession session){
        long count = 0;
        User user = (User) session.getAttribute("user");
        if(user != null){
            //获取未读的通知
            InformExample example = new InformExample();
            example.createCriteria().andReceiverEqualTo(user.getId())
                    .andStatusEqualTo(InformStatusEnum.UNREAD.getStatus());//未读消息
            count = informMapper.countByExample(example);
        }
        return count;
    }


}
