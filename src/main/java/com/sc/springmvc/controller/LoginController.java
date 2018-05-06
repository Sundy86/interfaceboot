package com.sc.springmvc.controller;

import com.sc.springmvc.model.Function;
import com.sc.springmvc.model.User;
import com.sc.springmvc.service.ILoginService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    @Resource
    private ILoginService loginService;

    @Resource
    private HttpServletRequest request;


    @RequestMapping(value = "/login.do")
    public String login(User user){
        if(StringUtils.isEmpty(user.getUsername())){
            request.setAttribute("error","用户名不能为空!");
            return "index";
        }
        if(StringUtils.isEmpty(user.getPassword())){
            request.setAttribute("error","密码不能为空!");
            return "index";
        }
        String vcode =(String)request.getSession().getAttribute("verifycode");
        if(!user.getCode().equalsIgnoreCase(vcode)){
            request.setAttribute("error","验证码错误！");
            return "index";
        }

        request.setAttribute("username",user.getUsername());
      return loginService.checkUser(request,user);
    }


    @RequestMapping(value = "/menu.do")
    public String menu(){
        List<Function> functionList = loginService.menu(request);
        request.setAttribute("functionList",functionList);
        return "menu";
    }

    @RequestMapping(value = "/loginout.do")
    public void loginOut(HttpServletResponse response){
        loginService.loginOut(request);
        try {
            response.getWriter().write("<script>window.parent.location='"+request.getContextPath()+"/index.jsp';</script>");
            response.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
