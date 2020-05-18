package org.zyx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zyx.entity.PagingData;
import org.zyx.enums.InformEnum;
import org.zyx.enums.InformStatusEnum;
import org.zyx.exception.CustomizeErrorCode;
import org.zyx.model.Inform;
import org.zyx.model.InformExample;
import org.zyx.model.User;
import org.zyx.repository.InformMapper;
import org.zyx.service.QuestionService;

import javax.servlet.http.HttpServletRequest;


/**主页请求
 * Created by SunShine on 2020/4/15.
 */
@Controller
public class IndexHandler {


    @Autowired
    private QuestionService questionService;
    @Autowired
    private InformMapper informMapper;

    public  String search = "";

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        @RequestParam(name="currentPage", required=false, defaultValue="1") Integer currentPage,
                        @RequestParam(name="count", required=false, defaultValue="5") Integer count,
                        @RequestParam(name="search", required=false, defaultValue ="") String search,
                        @RequestParam(name="index", required=false, defaultValue ="") String index){

        //处理主页的问题数据,判断是否有进行搜索
        if (search.length() > 0){
            this.search = search;
        }
        if(index.length() > 0){
            this.search = "";
        }

        PagingData pagingData=questionService.findQuestion(this.search,currentPage,count);
        request.getSession().setAttribute("pagingData",pagingData);



        return "index";
    }


}
