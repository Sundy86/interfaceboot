package com.sc.springmvc.service;

import com.sc.springmvc.model.Role;
import com.sc.springmvc.model.User;

import java.util.List;
import java.util.Map;

public interface IUserService {
     Map<String,Object> userlist(Integer pageNum, Integer pageSize);
     int adduser(User user);
     List<User> toadduser();
     int deleteuser(String userid);
     int updateuser(User user);
     Map<String,Object> toupdateuser(String userid);
     String check(String username);
     User getUser(String userid);
     List<Role> getRole();

}
