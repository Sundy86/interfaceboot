package com.sc.springmvc.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sc.springmvc.dao.CaseSystemMapper;
import com.sc.springmvc.model.CaseSystem;
import com.sc.springmvc.model.CaseSystemCriteria;
import com.sc.springmvc.service.ICaseSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class CaseSystemServiceImpl implements ICaseSystemService {

    @Autowired
    CaseSystemMapper caseSystemMapper;

    @Override
    public Map<String, Object> casesystemlist(Integer pageNum, Integer pageSize) {
        if(pageNum==null){
            pageNum=1;
        }
        if(pageSize==null || pageSize==0){
            pageSize=10;
        }

        Map<String,Object> data = new HashMap<String,Object>();
        PageHelper.startPage(pageNum,pageSize);

        CaseSystemCriteria cs = new CaseSystemCriteria();
        List<CaseSystem> casesystemList = caseSystemMapper.selectByExample(cs);
        PageInfo<CaseSystem> pageInfo = new PageInfo<CaseSystem>(casesystemList);

        data.put("totalPages",pageInfo.getPages());
        data.put("pageNum",pageInfo.getPageNum());
        data.put("pageSize",pageInfo.getPageSize());
        data.put("casesystemList",casesystemList);

        return data;
    }

    @Override
    public int addcasesystem(CaseSystem caseSystem) {
        caseSystem.setSystemid(UUID.randomUUID().toString());
        return caseSystemMapper.insert(caseSystem);
    }

    @Override
    public int updatecasesystem(CaseSystem caseSystem) {
        return caseSystemMapper.updateByPrimaryKey(caseSystem);
    }

    @Override
    public CaseSystem toupdatecasesystem(String systemid) {
        CaseSystem casesystem = caseSystemMapper.selectByPrimaryKey(systemid);
        return casesystem;
    }

    @Override
    public int deletecasesystem(String systemid) {
        return caseSystemMapper.deleteByPrimaryKey(systemid);
    }

    @Override
    public String check(String systemname) {
        CaseSystemCriteria cc = new CaseSystemCriteria();
        cc.createCriteria().andSystemnameEqualTo(systemname);
        List<CaseSystem> caseSystems = caseSystemMapper.selectByExample(cc);
        String s="";
        if(caseSystems.size()>0){
            s="Y";
        }else{
            s="N";
        }
        return s;
    }

    @Override
    public List<CaseSystem> getAll() {
        return caseSystemMapper.selectByExample(null);
    }
}
