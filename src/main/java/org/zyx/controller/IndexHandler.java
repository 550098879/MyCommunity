package org.zyx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zyx.entity.PagingData;
import org.zyx.service.QuestionService;

import javax.servlet.http.HttpServletRequest;


/**主页请求
 * Created by SunShine on 2020/4/15.
 */
@Controller
public class IndexHandler {


    @Autowired
    private QuestionService questionService;


    @GetMapping("/")
//    @RequestParam(name="name", required=false, defaultValue="World") String name, Model model
    public String index(HttpServletRequest request,
                        @RequestParam(name="currentPage", required=false, defaultValue="1") Integer currentPage,
                        @RequestParam(name="count", required=false, defaultValue="5") Integer count){


        //处理主页的问题数据
        PagingData pagingData=questionService.findQuestion((currentPage-1)*count,count);
        request.getSession().setAttribute("pagingData",pagingData);

        return "index";
    }








}
