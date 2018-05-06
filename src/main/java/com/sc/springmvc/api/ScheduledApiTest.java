package com.sc.springmvc.api;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.testng.TestNG;
import org.testng.collections.Lists;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * Created by Suncy on 2018/4/22 0022.
 */
@Component
public class ScheduledApiTest {
    /**
     * 心跳更新。启动时执行一次，之后每隔1分钟执行一次
     */
    @Scheduled(cron = "0 0/1 * * * ?") //每天凌晨两点执行 (0 0 2 * * ?)
    void doSomethingWith() throws FileNotFoundException {
        System.out.println("doSomethingWith---------");
        TestNG testng = new TestNG();
        List suites = Lists.newArrayList();
        File file = ResourceUtils.getFile("classpath:testng.xml");
        suites.add(file.getPath());//path to xml..
        testng.setTestSuites(suites);
        testng.run();
    }
}
