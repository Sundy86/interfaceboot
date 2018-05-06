package com.sc.springmvc.service;

import com.sc.springmvc.model.Function;

import java.util.List;
import java.util.Map;

public interface IFunctionService {
     Map<String,Object> functionlist(Integer pageNum, Integer pageSize);
     int addfunction(Function function);
     List<Function> toaddfunction();
     int deletefunction(String funcid);
     int updatefunction(Function function);
     Map<String,Object> toupdatefunction(String funcid);
     String check(String funcname);

}
