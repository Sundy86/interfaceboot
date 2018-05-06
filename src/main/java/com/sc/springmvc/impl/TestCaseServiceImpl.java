package com.sc.springmvc.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sc.springmvc.dao.TestCaseMapper;
import com.sc.springmvc.listener.ObjectCache;
import com.sc.springmvc.model.CaseSystem;
import com.sc.springmvc.model.TestCase;
import com.sc.springmvc.model.TestCaseCriteria;
import com.sc.springmvc.service.ITestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Suncy on 2018/4/21 0021.
 */
@Service
public class TestCaseServiceImpl implements ITestCaseService {

    @Autowired
    private TestCaseMapper testCaseMapper;

    @Override
    public Map<String, Object> caselist(Integer pageNum, Integer pageSize) {
        if(pageNum==null){
            pageNum=1;
        }
        if(pageSize==null || pageSize==0){
            pageSize=10;
        }
        PageHelper.startPage(pageNum,pageSize);
        Map<String,Object> data = new HashMap<String,Object>();

        TestCaseCriteria tc = new TestCaseCriteria();
        List<TestCase> testCaseList =testCaseMapper.selectByExample(tc);
        PageInfo<TestCase> pageInfo = new PageInfo<TestCase>(testCaseList);

        data.put("pageNum",pageInfo.getPageNum());
        data.put("pageSize",pageInfo.getPageSize());
        data.put("totalPages",pageInfo.getTotal());
        data.put("testCaseList",pageInfo.getList());
        return data;
    }

    @Override
    public int addcase(TestCase testCase) {
        testCase.setUuid(UUID.randomUUID().toString());
        return testCaseMapper.insert(testCase);
    }

    @Override
    public int deletecase(String caseId) {
        return testCaseMapper.deleteByPrimaryKey(caseId);
    }

    @Override
    public TestCase getByCaseId(String caseId) {
        return testCaseMapper.selectByPrimaryKey(caseId);
    }

    @Override
    public int updatecase(TestCase testCase) {
        return testCaseMapper.updateByPrimaryKeySelective(testCase);
    }

    @Override
    public String check(String casename) {
        TestCaseCriteria tc = new TestCaseCriteria();
        tc.createCriteria().andCasenameEqualTo(casename);
        List<TestCase> cases = testCaseMapper.selectByExample(tc);
        String s = "";
        if(cases.size()>0){
            s="Y";
        }else{
            s="N";
        }
        return s;
    }

    @Override
    public List<CaseSystem> getSystem() {
        List<Object> objects = ObjectCache.getAllObject();
        List<CaseSystem> systems = new ArrayList<CaseSystem>();
        for(Object object:objects){
            if(object instanceof CaseSystem){
                systems.add((CaseSystem) object);
            }
        }
        return systems;
    }
}
