package com.sc.springmvc.controller;

import com.sc.springmvc.model.CaseSystem;
import com.sc.springmvc.service.ICaseSystemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@Controller
@RequestMapping(value = "/system")
public class CaseSystemController {

    @Resource
    HttpServletRequest request;

    @Resource
    ICaseSystemService caseSystemService;

    @RequestMapping(value = "/systemlist.do")
    public String casesystemList(Integer pageNum, Integer pageSize){
        Map<String,Object> data = caseSystemService.casesystemlist(pageNum,pageSize);
        request.setAttribute("data",data);
        return "system/systemList";
    }

    @RequestMapping(value = "/toaddsystem.do")
    public String toAddcaseSystem(){
        return "system/addSystem";
    }

    @RequestMapping(value = "addsystem.do")
    public String addCaseSystem(@ModelAttribute("caseSystem") CaseSystem caseSystem) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        caseSystemService.addcasesystem(caseSystem);
        return "redirect:systemlist.do";
    }

    @RequestMapping(value = "/toupdatesystem.do")
    public String toUpdateCaseSystem(String systemid){
        CaseSystem caseSystem = caseSystemService.toupdatecasesystem(systemid);
        request.setAttribute("system",caseSystem);
        return "system/updateSystem";
    }

    @RequestMapping(value = "/updatesystem.do")
     public String updateCaseSystem(@ModelAttribute("caseSystem") CaseSystem caseSystem) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        caseSystemService.updatecasesystem(caseSystem);
        return "redirect:systemlist.do";
     }

     @RequestMapping(value = "/deletesystem.do")
     public String deleteCaseSystem(String systemid){
         caseSystemService.deletecasesystem(systemid);
         return "redirect:systemlist.do";
     }

    @RequestMapping(value = "/checkname.do")
    @ResponseBody
     public String check(String systemname){
        return caseSystemService.check(systemname);
     }
}
