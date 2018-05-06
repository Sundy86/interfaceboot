package com.sc.springmvc.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sc.springmvc.dao.ResultMapper;
import com.sc.springmvc.model.Result;
import com.sc.springmvc.model.ResultCriteria;
import com.sc.springmvc.service.ITestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TestResultServiceImpl implements ITestResultService {
	
	@Autowired
    ResultMapper resultMapper;
	
	
	@Override
	public List<Result> getTestReuslt(int pagesize) {
		PageHelper.startPage(1, pagesize);
		ResultCriteria example = new ResultCriteria();
		example.setOrderByClause("runtime desc");
		List<Result> list = resultMapper.selectByExample(example);
		PageInfo<Result> pageInfo = new PageInfo<Result>(list);
		List<Result> pagelist = pageInfo.getList();
		return pagelist;
	}
}
