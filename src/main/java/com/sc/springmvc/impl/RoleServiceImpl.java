package com.sc.springmvc.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sc.springmvc.dao.FuncRoleMapper;
import com.sc.springmvc.dao.FunctionMapper;
import com.sc.springmvc.dao.RoleMapper;
import com.sc.springmvc.model.*;
import com.sc.springmvc.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private FuncRoleMapper funcRoleMapper;

    @Autowired
    private FunctionMapper functionMapper;

    @Override
    public Map<String, Object> rolelist(Integer pageNum, Integer pageSize) {
        if(pageNum==null){
            pageNum=1;
        }
        if(pageSize==null || pageSize==0){
            pageSize=10;
        }

        PageHelper.startPage(pageNum,pageSize);

        RoleCriteria rc = new RoleCriteria();
        rc.createCriteria();
        List<Role> roleList = roleMapper.selectByExample(rc);
        PageInfo<Role> pageInfo = new PageInfo<Role>(roleList);

        Map<String,Object> data = new HashMap<String,Object>();

        data.put("totalPages",pageInfo.getPages());
        data.put("pageNum",pageInfo.getPageNum());
        data.put("pageSize",pageInfo.getPageSize());
        data.put("roleList",pageInfo.getList());
        return data;
    }

    @Override
    public int addrole(Role role, String str) {
        role.setRoleid(UUID.randomUUID().toString());
        roleMapper.insert(role);

        RoleCriteria roleCriteria = new RoleCriteria();
        roleCriteria.createCriteria().andRolenameEqualTo(role.getRolename()).andDescriptionEqualTo(role.getDescription());
        List<Role> roles = roleMapper.selectByExample(roleCriteria);
        role = roles.get(0);

        String [] strs = {};
        if(str!=null && !str.equals("")){
            if(str.length()>0){
                if(str.contains(",")){
                    strs = str.split(",");
                }else{
                    if(!str.contains(",")){
                        strs[0]=str;
                    }
                }
            }
        }
        if(strs.length>0 && strs!=null){
            for(String s : strs){
                FuncRole funcRole = new FuncRole();
                funcRole.setFuncroleid(UUID.randomUUID().toString());
                funcRole.setFuncid(s);
                funcRole.setRoleid(role.getRoleid());
                funcRoleMapper.insert(funcRole);
            }
        }
        return 0;
    }

    @Override
    public List<Role> toaddrole() {
        RoleCriteria rc = new RoleCriteria();
        rc.createCriteria();
        List<Role> roleList = roleMapper.selectByExample(rc);
        return roleList;
    }

    @Override
    public int deleterole(String roleid) {
        return  roleMapper.deleteByPrimaryKey(roleid);
    }

    @Override
    public int updaterole(Role role) {
        Role oldrole = roleMapper.selectByPrimaryKey(role.getRoleid());
        oldrole.setRolename(role.getRolename());
        oldrole.setDescription(role.getDescription());
        oldrole.setStatus(role.getStatus());
        return roleMapper.updateByPrimaryKey(oldrole);
    }

    @Override
    public Map<String, Object> toupdaterole(String roleid) {
        Map<String, Object> map = new HashMap<String,Object>();
        FunctionCriteria functionCriteria = new FunctionCriteria();
        functionCriteria.createCriteria();
        List<Function> functions = functionMapper.selectByExample(functionCriteria);

        Role role = roleMapper.selectByPrimaryKey(roleid);

        FuncRoleCriteria funcRoleCriteria = new FuncRoleCriteria();
        funcRoleCriteria.createCriteria().andRoleidEqualTo(role.getRoleid());
        List<FuncRole> funcRoles =funcRoleMapper.selectByExample(funcRoleCriteria);

        map.put("functions",functions);
        map.put("role",role);
        map.put("funcRoles",funcRoles);

        return map;
    }

    @Override
    public String check(String rolename) {
        RoleCriteria rc = new RoleCriteria();
        rc.createCriteria().andRolenameEqualTo(rolename);
        List<Role> roles = roleMapper.selectByExample(rc);
        String s = "";
        if(roles.size()>0){
            s="Y";
        }else{
            s="N";
        }
        return s;
    }

    @Override
    public List<Role> getAll() {
      return roleMapper.selectByExample(null);
    }
}
