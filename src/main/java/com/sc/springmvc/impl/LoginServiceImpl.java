package com.sc.springmvc.impl;

import com.sc.springmvc.dao.FuncRoleMapper;
import com.sc.springmvc.dao.FunctionMapper;
import com.sc.springmvc.dao.RoleMapper;
import com.sc.springmvc.dao.UserMapper;
import com.sc.springmvc.model.*;
import com.sc.springmvc.service.ILoginService;
import com.sc.springmvc.utils.MD5CryptUtil;
import org.apache.commons.codec.digest.Md5Crypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoginServiceImpl implements ILoginService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private FuncRoleMapper funcRoleMapper;

    @Autowired
    private FunctionMapper functionMapper;

    public String checkUser(HttpServletRequest request, User user) {
        UserCriteria example = new UserCriteria();
        String password = user.getPassword();
        example.createCriteria().andUsernameEqualTo(user.getUsername());
        List<User> objs = userMapper.selectByExample(example);
        if(objs.size()>0){
            User user1 = objs.get(0);
            //获取后台的用户密码
            String md5PassSalt =  MD5CryptUtil.getSalts(user1.getPassword());
            //将用户输入的密码加密
            String pass = Md5Crypt.md5Crypt(password.getBytes(),md5PassSalt);

           //  String pass = DigestUtils.md5Hex(password +user1.getUserid());
            //判断用户输入的密码与后台密码是否一致
            if(!pass.equals(user1.getPassword())){
                request.setAttribute("error", "密码错误!");
                return "index";
            }else{
                request.getSession().setAttribute("user",user1);
//                RoleCriteria rc = new RoleCriteria();
//                rc.createCriteria().andRoleidEqualTo(user1.getRoleid());
//                List<Role> roles = roleMapper.selectByExample(rc);
//                if(roles.size()>0){
//                    request.getSession().setAttribute("rolelist",roles.get(0));
//                }
                return "main";
            }

        }else{
            request.setAttribute("error", "用户不存在!");
            return "index";
        }
    }

    public void loginOut(HttpServletRequest request) {
       request.getSession().removeAttribute("user");
    }

    @Override
    public List<Function> menu(HttpServletRequest request) {
        List<Function> functionList = new ArrayList<Function>();

        User user = (User) request.getSession().getAttribute("user");
        //测评过用户表中的roleid查询funcRole表中 该用户的拥有的功能和角色
        FuncRoleCriteria fr = new FuncRoleCriteria();
        fr.createCriteria().andRoleidEqualTo(user.getRoleid());
        //返回当前用户的功能和角色
        List<FuncRole> funcRoleList = funcRoleMapper.selectByExample(fr);

        //功能列表按func_order排序
        FunctionCriteria fc = new FunctionCriteria();
        fc.setOrderByClause("func_order");
        //查询所有的功能
        List<Function> functions = functionMapper.selectByExample(fc);

        for(Function function:functions){
            for(FuncRole funcRole:funcRoleList){
                //判断当前用户的功能存在于功能列表中时，将功能添加到指定list中
                if(funcRole.getFuncid().equals(function.getFuncid())){
                    functionList.add(function);
                }
            }
        }
        return functionList;
    }
}
