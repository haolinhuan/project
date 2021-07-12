package com.xuecheng.freemarker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Controller
@RequestMapping("/freemarker")
public class FreemarkerController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/test12")
    public String freemarker(Map<String, Object> map){
        map.put("name","黑马程序员");
        //返回模板文件名称
        return "test";
    }

}
