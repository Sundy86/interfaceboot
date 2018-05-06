package com.sc.springmvc.api;

import com.sc.springmvc.dao.TestCaseMapper;
import com.sc.springmvc.model.TestCase;
import com.sc.springmvc.model.TestCaseCriteria;
import com.sc.springmvc.utils.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Suncy on 2018/4/22 0022.
 */
public class ApiTest {
    @Test(dataProvider = "db")
    public void TestCase(TestCase testCase) throws HttpClientException {
        System.out.println("thread----"+  Thread.currentThread().getName());
        PatternUtils.matcher(testCase);
        System.out.println(testCase);
        String reString = null;
        if ("get".equalsIgnoreCase(testCase.getType())) {
            reString = HttpUtils.doGet(testCase.getUrl(), MapUtils.covertStringToMp(testCase.getHeader()));
        } else if ("post".equalsIgnoreCase(testCase.getType())) {
            reString = HttpUtils.doPost(testCase.getUrl(),MapUtils.covertStringToMp(testCase.getParams(), "&"),MapUtils.covertStringToMp(testCase.getHeader()));
        }else if ("postjson".equalsIgnoreCase(testCase.getType())) {
            reString = HttpUtils.doPostJson(testCase.getUrl(), testCase.getParams(), MapUtils.covertStringToMp(testCase.getHeader()));
        }
        System.out.println(reString);
        boolean check = CheckPointUtils.checkbyJsonPath(reString, testCase.getCheckpoint());

        if (check) {
            SaveParamsUtils.saveMapbyJsonPath(reString, testCase.getCorrelation());
        }
        Assert.assertTrue(check);
    }


//	    @DataProvider(name = "exceldata")
//	    public Iterator<Object[]> parameterIntTestProvider() {
//	    	List<Object[]> dataProvider = new ArrayList<Object[]>();
//	    	String path = System.getProperty("user.dir") + File.separator + "data" + File.separator;
//	    	try {
//				List<TestCase> list = ExcelUtils.getInstance().readExcel2Objects(path + "apitest.xlsx", TestCase.class);
//				for (TestCase testCase : list) {
//					if(testCase.isRun()) {
//						dataProvider.add(new Object[] { testCase});
//					}
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//	        return dataProvider.iterator();
//	    }

    @DataProvider(name = "db")
    public Iterator<Object[]> parameterIntTestProvider() {
        List<Object[]> dataProvider = new ArrayList<Object[]>();
        TestCaseMapper testCaseMapper = SpringContextUtils.getBean(TestCaseMapper.class);
        TestCaseCriteria c = new TestCaseCriteria();
        c.createCriteria().andRunEqualTo("Y");
        List<TestCase> list = testCaseMapper.selectByExampleWithBLOBs(c);
        for (com.sc.springmvc.model.TestCase TestCase : list) {
            dataProvider.add(new Object[] { TestCase });
        }

        return dataProvider.iterator();


    }
}
