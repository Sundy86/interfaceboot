package com.sc.springmvc.controller;

import com.sc.springmvc.model.Role;
import com.sc.springmvc.service.IRoleService;
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
@RequestMapping(value = "/role")
public class RoleController {

    @Resource
    private HttpServletRequest request;

    @Resource
    private IRoleService roleService;

    @RequestMapping(value = "/rolelist.do")
    public String roleList(Integer pageNum,Integer pageSize){

        Map<String,Object> data = roleService.rolelist(pageNum,pageSize);
        request.setAttribute("data",data);
        return "role/roleList";
    }

    @RequestMapping(value = "/toaddrole.do")
    public String toAddRole(){
       List<Role> list = roleService.toaddrole();
       request.setAttribute("roles",list);
        return "role/addrole";
    }

    @RequestMapping(value = "/addrole.do")
    public String addRole(@ModelAttribute("role")Role role, String str) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        roleService.addrole(role,str);
        return "redirect:rolelist.do";
    }

    @RequestMapping(value = "/deleterole.do")
    public String deleteRole(String roleid){
        roleService.deleterole(roleid);
       return "redirect:rolelist.do";
    }

    @RequestMapping(value = "/toupdaterole.do")
    public String toUpdateRole(String roleid){
        Map<String,Object> map = roleService.toupdaterole(roleid);
        request.setAttribute("data",map);
        return "role/updaterole";
    }

    @RequestMapping(value = "/updaterole.do")
    public String updateRole(@ModelAttribute("role")Role role) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        roleService.updaterole(role);
        return "redirect:rolelist.do";
    }

    @RequestMapping(value = "/checkname.do")
    @ResponseBody
    public String checkname(String rolename){
        return roleService.check(rolename);
    }
}
