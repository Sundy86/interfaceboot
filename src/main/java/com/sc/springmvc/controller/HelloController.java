package com.sc.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Suncy on 2018/5/6 0006.
 */
@Controller
@RequestMapping(value = "/hello")
public class HelloController {

    @ResponseBody
    @RequestMapping(value = "/test")
    public String hello(){
        return "hello springboot";
    }

    @RequestMapping(value = "/jsp")
    public String hello2(){
        return "hello";
    }
}
