package com.spring.product.springcloudproduct.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping(value="/getOrderUserAll",method = RequestMethod.GET)
    public @ResponseBody Object hello(){
        return "hello word";
    }
}
