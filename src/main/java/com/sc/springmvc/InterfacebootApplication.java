package com.sc.springmvc;

import com.sc.springmvc.listener.CacheLilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAutoConfiguration
@MapperScan("com.sc.springmvc.dao")
@EnableScheduling
@ImportResource(value={"classpath:/META-INF/serverContext.xml"})
public class InterfacebootApplication {

	public static void main(String[] args) {
		SpringApplication.run(InterfacebootApplication.class, args);
	}
	@Bean // 注册Listener
    public ServletListenerRegistrationBean<CacheLilter> servletListenerRegistrationBean() {
        return new ServletListenerRegistrationBean<CacheLilter>(new CacheLilter());
    }

//    @Bean // 注册Filter
//	public FilterRegistrationBean filterRegistrationBean() {
//		return new FilterRegistrationBean(new LoginFilter());
//	}
}
