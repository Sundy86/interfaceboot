package com.sc.springmvc.service;

import com.sc.springmvc.model.CaseSystem;

import java.util.List;
import java.util.Map;

public interface ICaseSystemService {
     Map<String,Object> casesystemlist(Integer pageNum, Integer pageSize);
     int addcasesystem(CaseSystem caseSystem);
     int updatecasesystem(CaseSystem caseSystem);
     CaseSystem toupdatecasesystem(String systemid);
     int deletecasesystem(String systemid);
     String check(String systemname);
     List<CaseSystem> getAll();

}
