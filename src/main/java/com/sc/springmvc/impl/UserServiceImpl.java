package com.sc.springmvc.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sc.springmvc.dao.RoleMapper;
import com.sc.springmvc.dao.UserMapper;
import com.sc.springmvc.listener.ObjectCache;
import com.sc.springmvc.model.Role;
import com.sc.springmvc.model.RoleCriteria;
import com.sc.springmvc.model.User;
import com.sc.springmvc.model.UserCriteria;
import com.sc.springmvc.service.IUserService;
import org.apache.commons.codec.digest.Md5Crypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    RoleMapper roleMapper;

    @Override
    public Map<String, Object> userlist(Integer pageNum, Integer pageSize) {
        if(pageNum==null){
            pageNum=1;
        }
        if(pageSize==null || pageSize==0){
            pageSize=10;
        }
        PageHelper.startPage(pageNum,pageSize);
        Map<String,Object> map = new HashMap<String,Object>();
        UserCriteria uc = new UserCriteria();
        List<User> userList = userMapper.selectByExample(uc);
        PageInfo<User> pageInfo = new PageInfo<User>(userList);

        map.put("objs",pageInfo.getList());
        map.put("pag",pageInfo.getPageNum());
        map.put("pagesize",pageInfo.getPageSize());
        map.put("total",pageInfo.getTotal());

        return map;
    }

    @Override
    public int adduser(User user) {

       user.setUserid(UUID.randomUUID().toString());
       user.setPassword(Md5Crypt.md5Crypt(user.getPassword().getBytes()));
       // user.setPassword(DigestUtils.md5Hex(user.getPassword()+user.getUserid()));
        return  userMapper.insert(user);
    }

    @Override
    public List<User> toadduser() {
        return null;
    }

    @Override
    public int deleteuser(String userid) {
        return userMapper.deleteByPrimaryKey(userid);
    }

    @Override
    public int updateuser(User user) {
        User olduser =userMapper.selectByPrimaryKey(user.getUserid());
        olduser.setAddr(user.getAddr());
        olduser.setBianhao(user.getBianhao());
        olduser.setDescription(user.getDescription());
        olduser.setCode(user.getCode());
        olduser.setEmail(user.getEmail());
        olduser.setName(user.getName());
        olduser.setSex(user.getSex());
        olduser.setStatus(user.getStatus());
        olduser.setRoleid(user.getRoleid());
        olduser.setPassword(Md5Crypt.md5Crypt(user.getPassword().getBytes()));
        return userMapper.updateByPrimaryKey(olduser);
    }

    @Override
    public Map<String, Object> toupdateuser(String userid) {
        Map<String,Object> map = new HashMap<String,Object>();
        if(userid.length()>0&&userid!=null){
           User user = userMapper.selectByPrimaryKey(userid);
           map.put("user",user);
        }

        RoleCriteria rc = new RoleCriteria();
        rc.createCriteria();
        List<Role> roles = roleMapper.selectByExample(rc);
        map.put("roles",roles);
        return map;
    }

    @Override
    public String check(String username) {
        UserCriteria uc = new UserCriteria();
        uc.createCriteria().andUsernameEqualTo(username);
        List<User> list =userMapper.selectByExample(uc);
        String s=null;
        if(list.size()>0 && list!=null){
            s="Y";
        }else{
            s="N";
        }
        return s;
    }

    @Override
    public User getUser(String userid) {
        return userMapper.selectByPrimaryKey(userid);
    }

    @Override
    public List<Role> getRole() {
        List<Object> objects = ObjectCache.getAllObject();
        List<Role> roles = new ArrayList<Role>();
        for(Object object:objects){
            if(object instanceof Role){
                roles.add((Role) object);
            }
        }
        return roles;
    }
}
