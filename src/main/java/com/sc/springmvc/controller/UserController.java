package com.sc.springmvc.controller;

import com.sc.springmvc.model.User;
import com.sc.springmvc.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    IUserService userService;
    @Autowired
    HttpServletRequest request;

    @RequestMapping(value = "/userlist.do")
    public String userList(Integer pageNum, Integer pageSize){
        Map<String,Object> map = userService.userlist(pageNum,pageSize);
        request.setAttribute("data",map);
        return "user/userList";
    }

    @RequestMapping(value = "/toadduser.do")
    public String toAddUser(){
        request.setAttribute("rolenames",userService.getRole());
        return "user/addUser";
    }

    @RequestMapping(value = "/adduser.do")
    public String addUser(@ModelAttribute("user") User user){
        userService.adduser(user);
        return "redirect:userlist.do";
    }
    @RequestMapping(value = "/toupdateuser.do")
    public String toUpdateUser(String userid){
        User user = userService.getUser(userid);
        request.setAttribute("user",user);
        request.setAttribute("rolenames",userService.getRole());
        return "user/updateUser";
    }

    @RequestMapping(value = "/updateuser.do")
    public String updateUser(@ModelAttribute("user") User user){
        userService.updateuser(user);
        return "redirect:userlist.do";
    }

    @RequestMapping(value = "/deleteuser.do")
    public String deleteUser(String userid){
        userService.deleteuser(userid);
        return "redirect:userlist.do";
    }

    @RequestMapping(value = "/checkusername.do")
    @ResponseBody
    public String check(String username){
       return userService.check(username);
    }
}
