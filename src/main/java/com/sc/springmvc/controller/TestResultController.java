package com.sc.springmvc.controller;

import com.sc.springmvc.model.Result;
import com.sc.springmvc.service.ITestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/report")
public class TestResultController {

    @Autowired
    ITestResultService reportService;

    @ResponseBody
    @RequestMapping(value = "/result.do")
    public List<Result> getTestReulst(int pageSize){
        return reportService.getTestReuslt(pageSize);
    }

}
