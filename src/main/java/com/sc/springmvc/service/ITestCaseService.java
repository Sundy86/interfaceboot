package com.sc.springmvc.service;


import com.sc.springmvc.model.CaseSystem;
import com.sc.springmvc.model.TestCase;

import java.util.List;
import java.util.Map;

public interface ITestCaseService {
     Map<String,Object> caselist(Integer pageNum, Integer pageSize);
     int addcase(TestCase testCase);
     int deletecase(String caseId);
    TestCase getByCaseId(String caseId);
     int updatecase(TestCase testCase);
    String check(String casename);
    List<CaseSystem> getSystem();

}
