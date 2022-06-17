package com.atguigu.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class ScopeController {

    //使用servletAPI向request域对象共享数据
    @RequestMapping("/testRequestByServletAPI")
    public String testRequestByServletAPI(HttpServletRequest request){
        request.setAttribute("testRequestScope", "hello,servletAPI");
        return "success";
    }


    @RequestMapping("/testModelAndView")
    public ModelAndView testModelAndView(){

        ModelAndView mav = new ModelAndView();
        //处理模型数据，向请求域request共享数据
        mav.addObject("testRequestScope", "hello,ModelAndView");
        //设置视图名称
        mav.setViewName("success");

        return mav;
    }

    @RequestMapping("/testModel")
    public String testModel(Model model){
        model.addAttribute("testRequestScope", "hello,Model");
        System.out.println(model);
        return "success";
    }

    @RequestMapping("/testMap")
    public String testMap(Map<String, Object> map){
        map.put("testRequestScope", "hello,Map");
        System.out.println(map);
        return "success";
    }

    @RequestMapping("/testModelMap")
    public String testModelMap(ModelMap modelMap){
        modelMap.addAttribute("testRequestScope", "hello,ModelMap");
        System.out.println(modelMap);
        return "success";
    }

    @RequestMapping("/testSession")
    public String testSession(HttpSession httpSession){
        httpSession.setAttribute("testSessionScope", "hello,Session");
        return "success";
    }

    @RequestMapping("/testApplication")
    public String testApplication(HttpSession session){

        ServletContext application = session.getServletContext();
        application.setAttribute("testApplicationScope", "hello,Application");
        return "success";
    }

}
