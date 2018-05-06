package com.sc.springmvc.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet Filter implementation class MyTestFilter
 */
@WebFilter("/LoginFilter")
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		String requestURI = httpRequest.getRequestURI().substring(httpRequest.getRequestURI().indexOf("/"),httpRequest.getRequestURI().length());
		System.out.println(requestURI+"------------");
		//String requestURI = httpRequest.getRequestURI().substring(httpRequest.getRequestURI().indexOf("/",1),httpRequest.getRequestURI().length());

        if("/login/login.do".equals(requestURI)||"/verifycode/getVerifyCodeImage.do".equals(requestURI))
        {  
        	System.out.println(requestURI);
        	chain.doFilter(request, response);
        }else{
        	System.out.println(requestURI);
        	 Object  object = httpRequest.getSession().getAttribute("user");
    		 if(object==null){
    			httpResponse.sendRedirect(httpRequest.getContextPath()+"/index.jsp");
    			return;
    		 }else{
    			chain.doFilter(request, response);
    		 }
        }
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		}

}
