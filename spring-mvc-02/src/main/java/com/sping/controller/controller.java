package com.sping.controller;

import com.sping.bean.User;
import com.sping.exception.SystemException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
@RequestMapping("/v1")
public class controller {

    @RequestMapping(path = "/hello")
    public String sayHello(){
        System.out.println("hello SpringMvc");
        return "success";
    }


    @RequestMapping(path = "/model")
    public ModelAndView sayModel(ModelAndView mode){
        ModelAndView model = new ModelAndView();
        System.out.println("hello ModelAndView");
        User user   = new User();
        user.setName("郝林欢");
        user.setAge("18");
        user.setBirthday(new Date());
        model.addObject("user",user);
        // 跳转到哪个页面
//        model.setViewName("success");
        return  model;
    }


    @RequestMapping(path = "/exception")
    public String exception() throws SystemException {
        System.out.println("hello exception");
        try{
            int i = 1/0;
        }catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("好了");
        }
        return "success";
    }

    @RequestMapping(path = "/interceptor")
    public String interceptor()  {
        System.out.println("hello interceptor");
        return "success";
    }


}
