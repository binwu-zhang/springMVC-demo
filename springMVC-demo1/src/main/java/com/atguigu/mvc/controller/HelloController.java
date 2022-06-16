package com.atguigu.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
    @RequestMapping("/")
    public String index(){
        System.out.println("aaaaa");
        return "index";
    }

    @RequestMapping("/target")
    public String target(){
        return "target";
    }
}
