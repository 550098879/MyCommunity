package org.zyx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by SunShine on 2020/4/15.
 */
@Controller
@RequestMapping("/hello")
public class HelloHandler {


    @GetMapping("/")
//    @RequestParam(name="name", required=false, defaultValue="World") String name, Model model
    public String index(){
        return "index";
    }

}
