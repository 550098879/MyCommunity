package org.zyx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zyx.entity.PagingData;
import org.zyx.entity.User;
import org.zyx.repository.UserRepository;
import org.zyx.service.QuestionService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


/**主页请求
 * Created by SunShine on 2020/4/15.
 */
@Controller
public class IndexHandler {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private QuestionService questionService;


    @GetMapping("/")
//    @RequestParam(name="name", required=false, defaultValue="World") String name, Model model
    public String index(HttpServletRequest request,
                        @RequestParam(name="currentPage", required=false, defaultValue="1") Integer currentPage,
                        @RequestParam(name="count", required=false, defaultValue="2") Integer count){

        //处理自动登录功能
        Cookie cookies[]=request.getCookies();
        if(cookies==null){
            System.out.println("cookie为空");
        }else{
            for(Cookie cookie : cookies){
                if("token".equals(cookie.getName())){
                    String token=cookie.getValue();
                    User user = userRepository.findByToken(token);
                    if(user!=null){
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        }
        //处理主页的问题数据
        PagingData pagingData=questionService.findQuestion(currentPage,count);
        request.getSession().setAttribute("pagingData",pagingData);

        return "index";
    }








}
