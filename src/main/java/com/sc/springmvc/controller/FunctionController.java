package com.sc.springmvc.controller;

import com.sc.springmvc.model.Function;
import com.sc.springmvc.service.IFunctionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/function")
public class FunctionController {

    @Resource
    private HttpServletRequest request;

    @Resource
    private IFunctionService functionService;

    @RequestMapping(value = "/functionlist.do")
    public String functionList(Integer pageNum,Integer pageSize){

        Map<String,Object> data = functionService.functionlist(pageNum,pageSize);
        request.setAttribute("data",data);
        return "function/functionList";
    }

    @RequestMapping(value = "/toaddfunction.do")
    public String toAddFunction(){
       List<Function> list = functionService.toaddfunction();
       request.setAttribute("functions",list);
        return "function/addfunction";
    }

    @RequestMapping(value = "/addfunction.do")
    public String addFunction(@ModelAttribute("function")Function function) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        functionService.addfunction(function);
        return "redirect:functionlist.do";
    }

    @RequestMapping(value = "/deletefunction.do")
    public String deleteFunction(String funcid){
       functionService.deletefunction(funcid);
       return "redirect:functionlist.do";
    }

    @RequestMapping(value = "/toupdatefunction.do")
    public String toUpdateFunction(String funcid){
        Map<String,Object> map = functionService.toupdatefunction(funcid);
        request.setAttribute("data",map);
        return "function/updatefunction";
    }

    @RequestMapping(value = "/updatefunction.do")
    public String updateFunction(@ModelAttribute("function")Function function) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        functionService.updatefunction(function);
        return "redirect:functionlist.do";
    }

    @RequestMapping(value = "/checkname.do")
    @ResponseBody
    public String checkname(String funcname){
        return  functionService.check(funcname);
    }
}
