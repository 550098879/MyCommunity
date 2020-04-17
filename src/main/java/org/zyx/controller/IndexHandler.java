package org.zyx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.zyx.entity.User;
import org.zyx.repository.UserRepository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by SunShine on 2020/4/15.
 */
@Controller
public class IndexHandler {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
//    @RequestParam(name="name", required=false, defaultValue="World") String name, Model model
    public String index(HttpServletRequest request){

        Cookie cookies[]=request.getCookies();

        for(Cookie cookie : cookies){
            if("token".equals(cookie.getName())){
                String token=cookie.getValue();
                User user = userRepository.findByToken(token);
                if(user!=null){
                    System.out.println(user);
                    request.getSession().setAttribute("user",user);
                }
                break;
            }
        }
        return "index";
    }





}
