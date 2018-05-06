package com.sc.springmvc.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sc.springmvc.dao.FuncRoleMapper;
import com.sc.springmvc.dao.FunctionMapper;
import com.sc.springmvc.dao.RoleMapper;
import com.sc.springmvc.model.*;
import com.sc.springmvc.service.IFunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class FunctionServiceImpl implements IFunctionService {

    @Autowired
    private FunctionMapper functionMapper;

    @Autowired
    private FuncRoleMapper funcRoleMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Map<String, Object> functionlist(Integer pageNum, Integer pageSize) {
        if(pageNum==null){
            pageNum=1;
        }
        if(pageSize==null || pageSize==0){
            pageSize=10;
        }

        Map<String,Object> data = new HashMap<String,Object>();

        PageHelper.startPage(pageNum,pageSize);

        FunctionCriteria fc = new FunctionCriteria();
        fc.setOrderByClause("func_order");
        List<Function> functionList = functionMapper.selectByExample(fc);
        PageInfo<Function> pageInfo = new PageInfo<Function>(functionList);

        FunctionCriteria fc_parent = new FunctionCriteria();
        fc_parent.createCriteria().andParentidEqualTo("0");
        List<Function> function_parent = functionMapper.selectByExample(fc_parent);

        data.put("totalPages",pageInfo.getPages());
        data.put("pageNum",pageInfo.getPageNum());
        data.put("pageSize",pageInfo.getPageSize());
        data.put("funcList",pageInfo.getList());
        data.put("funcParentlist",function_parent);

        return data;
    }

    @Override
    public int addfunction(Function function) {
        function.setFuncid(UUID.randomUUID().toString());
        RoleCriteria roleCriteria = new RoleCriteria();
        roleCriteria.createCriteria().andStatusEqualTo("super");
        List<Role> roles = roleMapper.selectByExample(roleCriteria);
        for(Role role:roles){
            FuncRole fr = new FuncRole();
            fr.setFuncid(function.getFuncid());
            fr.setRoleid(role.getRoleid());
            fr.setFuncroleid(UUID.randomUUID().toString());
            funcRoleMapper.insert(fr);
        }
        return functionMapper.insert(function);
    }

    @Override
    public List<Function> toaddfunction() {
        FunctionCriteria fc = new FunctionCriteria();
        fc.createCriteria().andParentidEqualTo("0");
        List<Function> functionList = functionMapper.selectByExample(fc);
        return functionList;
    }

    @Override
    public int deletefunction(String funcid) {
        return  functionMapper.deleteByPrimaryKey(funcid);
    }

    @Override
    public int updatefunction(Function function) {
        Function oldfun = functionMapper.selectByPrimaryKey(function.getFuncid());
        oldfun.setUrl(function.getUrl());
        oldfun.setFuncOrder(function.getFuncOrder());
        oldfun.setParentid(function.getParentid());
        oldfun.setFuncname(function.getFuncname());
        return functionMapper.updateByPrimaryKey(oldfun);
    }

    @Override
    public Map<String, Object> toupdatefunction(String funcid) {
        Map<String, Object> map = new HashMap<String,Object>();
        Function function = functionMapper.selectByPrimaryKey(funcid);

        FunctionCriteria fc = new FunctionCriteria();
        fc.createCriteria().andParentidEqualTo("0");
        List<Function> functionList = functionMapper.selectByExample(fc);
        map.put("functionList",functionList);
        map.put("function",function);
        return map;
    }

    @Override
    public String check(String funcname) {
        FunctionCriteria fc = new FunctionCriteria();
        fc.createCriteria().andFuncnameNotEqualTo(funcname);
        List<Function> func = functionMapper.selectByExample(fc);
        String s="";
        if(func.size()>0){
            s="Y";
        }else{
            s="N";
        }
        return s;
    }


}
