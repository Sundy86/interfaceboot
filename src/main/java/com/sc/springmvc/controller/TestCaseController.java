package com.sc.springmvc.controller;

import com.sc.springmvc.model.TestCase;
import com.sc.springmvc.service.ITestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Suncy on 2018/4/21 0021.
 */
@Controller
@RequestMapping(value = "/case")
public class TestCaseController {
    @Autowired
    ITestCaseService testCaseService;
    @Autowired
    HttpServletRequest request;

    @RequestMapping(value = "/caselist.do")
    public String caseList(Integer pageNum, Integer pageSize){
        Map<String,Object> data = testCaseService.caselist(pageNum,pageSize);
        request.setAttribute("data",data);
        return "case/caseList";
    }
    @RequestMapping(value = "/toaddcase.do")
    public String toAddCase(){
        request.setAttribute("systemnames",testCaseService.getSystem());
        return "case/addCase";
    }
    @RequestMapping(value = "/addcase.do")
    public String addCase(@ModelAttribute("testCase") TestCase testCase){
        testCaseService.addcase(testCase);
        return "redirect:caselist.do";
    }
    @RequestMapping(value = "/toupdatecase.do")
    public String toUpdateCase(String caseId){
        TestCase testCase =testCaseService.getByCaseId(caseId);
        request.setAttribute("case",testCase);
        request.setAttribute("systemnames",testCaseService.getSystem());
        return "case/updateCase";
    }
    @RequestMapping(value = "/updatecase.do")
    public String updateCase(@ModelAttribute("testCase") TestCase testCase){
        testCaseService.updatecase(testCase);
        return "redirect:caselist.do";
    }
    @RequestMapping(value = "/deletecase.do")
    public String deleteCase(String caseId){
        testCaseService.deletecase(caseId);
        return "redirect:caselist.do";
    }
    @RequestMapping(value = "/checkname.do")
    @ResponseBody
    public String check(String casemname){
        return testCaseService.check(casemname);
    }

}
