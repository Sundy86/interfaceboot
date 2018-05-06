package com.sc.springmvc.service;

import com.sc.springmvc.model.Result;

import java.util.List;

public interface ITestResultService {

	List<Result> getTestReuslt(int pagesize);

}