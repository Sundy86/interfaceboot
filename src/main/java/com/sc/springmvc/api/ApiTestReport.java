package com.sc.springmvc.api;

import com.sc.springmvc.dao.ResultDetailMapper;
import com.sc.springmvc.dao.ResultMapper;
import com.sc.springmvc.mail.MailSender;
import com.sc.springmvc.model.Result;
import com.sc.springmvc.model.ResultDetail;
import com.sc.springmvc.model.TestCase;
import com.sc.springmvc.utils.EmailUtils;
import com.sc.springmvc.utils.SpringContextUtils;
import freemarker.template.Template;
import org.apache.commons.mail.EmailException;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.testng.*;
import org.testng.collections.Lists;
import org.testng.xml.XmlSuite;

import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class ApiTestReport  implements ITestListener, IReporter {
	private List<ResultDetail> testPassed = Lists.newArrayList();
	private List<ResultDetail> testFailed = Lists.newArrayList();
	private List<ErrorReport> errorList = Lists.newArrayList();


	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		System.out.println(" generateReport-------------- ");
		System.out.println("Passed Case: " + testPassed.size());
		System.out.println("testFailed Case: " + testFailed.size());
		Date date = new Date();
		Result result = new Result();
		result.setRuntime(date);
		result.setSucess(testPassed.size());
		result.setFail(testFailed.size());
		ResultMapper testResultMapper = SpringContextUtils.getBean(ResultMapper.class);
		testResultMapper.insertSelective(result);
		ResultDetailMapper testResultDetailMapper = SpringContextUtils.getBean(ResultDetailMapper.class);


		testPassed.addAll(testFailed);
		for (ResultDetail testResultDetail : testPassed) {
			testResultDetail.setRuntime(date);
			try{
				testResultDetailMapper.insertSelective(testResultDetail);
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}

        if(!testFailed.isEmpty()){
         HashMap<String,Object> map = new HashMap<String,Object>();
		 map.put("runtime", date);
		 map.put("reportlist", errorList);
            try {
                EmailUtils.sendEmailsWithAttachments("错误信息", getContext(map));
            } catch (EmailException e) {
                e.printStackTrace();
            }
            // MailSender mailSender = SpringContextUtils.getBean(MailSender.class);
            // mailSender.send(map,"error.ftl");
        }

	}

	@Override
	public void onTestStart(ITestResult result) {
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		Object o=  result.getParameters()[0];
		TestCase testCase =(TestCase)o;
		long start = result.getStartMillis();
		long end = result.getEndMillis();
		ResultDetail resultDetail = new ResultDetail();
		resultDetail.setCaseid(testCase.getUuid());
		resultDetail.setResult("Y");
		resultDetail.setTaketime(end-start);
		resultDetail.setSystem(testCase.getSystem());
		testPassed.add(resultDetail);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		Object o=  result.getParameters()[0];
		TestCase testCase =(TestCase)o;
		long start = result.getStartMillis();
		long end = result.getEndMillis();

		ResultDetail resultDetail = new ResultDetail();
		resultDetail.setCaseid(testCase.getUuid());
		resultDetail.setResult("N");
		resultDetail.setTaketime(end-start);
		resultDetail.setSystem(testCase.getSystem());
		String msg = result.getThrowable().getMessage();
		System.out.println(msg);
		resultDetail.setDetailmsg(msg);
		testFailed.add(resultDetail);
		ErrorReport error = new ErrorReport();
		error.setCaseName(testCase.getCasename());
		error.setUrl(testCase.getUrl());
		error.setMsg(msg);
		errorList.add(error);
		System.out.println(error);
		result.getThrowable();
	}
    private String getContext(HashMap<String, Object> map) {
        FreeMarkerConfigurer freeMarkerConfigurer = SpringContextUtils.getBean(FreeMarkerConfigurer.class);
        Template template;
        String text = null;
        try {
            template = freeMarkerConfigurer.getConfiguration().getTemplate("error.ftl");
            // map中的key，对应模板中的${key}表达式
            text = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return text;
    }


	@Override
	public void onTestSkipped(ITestResult result) {

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {
	}

	@Override
	public void onFinish(ITestContext context) {
	}
}