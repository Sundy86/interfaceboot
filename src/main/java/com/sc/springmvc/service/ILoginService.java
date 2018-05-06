package com.sc.springmvc.service;

import com.sc.springmvc.model.Function;
import com.sc.springmvc.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ILoginService {
     String checkUser(HttpServletRequest request, User user);
     void loginOut(HttpServletRequest request);
     List<Function> menu(HttpServletRequest request);
}
