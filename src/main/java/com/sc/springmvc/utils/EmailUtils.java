package com.sc.springmvc.utils;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import java.io.File;

public class EmailUtils {
	
	
	public static void sendMsg() throws EmailException {
		HtmlEmail mail = new HtmlEmail();
		String hostname  = PropertiesUtils.getString("mail.host");
		String password = PropertiesUtils.getString("mail.password");
		String username = PropertiesUtils.getString("mail.username");
	    String[] toList = PropertiesUtils.getStringArray("mail.touser");
	    String subject = PropertiesUtils.getString("mail.title");
		mail.setHostName(hostname); // 邮件服务器域�?
		//mail.setSmtpPort(smtpPort); // 邮件服务器smtp协议端口
		mail.setAuthentication(username, password); // 邮箱账户
		mail.setCharset("UTF-8"); // 邮件的字符集

		//mail.setSSLOnConnect(true); // 是否启用SSL
		//mail.setSslSmtpPort(sslSmtpPort); // 若启用，设置smtp协议的SSL端口�?
 
		mail.setFrom(username); // 发件人地�?
		for (String to : toList) {
			mail.addTo(to); // 收件人地�?，可以设置多�?
		}
		
		mail.setSubject(subject); // 邮件主题  
		mail.setHtmlMsg("test"); // 邮件正文  
		String rString = mail.send(); // 发�?�邮�?  
		System.out.println(rString);
	}
	
	 public static void sendEmailsWithAttachments(String title, String context, String... filepath) throws EmailException {

	        String hostname  = PropertiesUtils.getString("mail.host");
			String password = PropertiesUtils.getString("mail.password");
			String username = PropertiesUtils.getString("mail.username");
		    String[] toList = PropertiesUtils.getStringArray("mail.touser");
		    
	        // Create the email message
		    HtmlEmail email = new HtmlEmail();
	        email.setHostName(hostname); // 邮件服务器域�?
			//mail.setSmtpPort(smtpPort); // 邮件服务器smtp协议端口
	        email.setAuthentication(username, password); // 邮箱账户
	        email.setCharset("UTF-8"); // 邮件的字符集

			//mail.setSSLOnConnect(true); // 是否启用SSL
			//mail.setSslSmtpPort(sslSmtpPort); // 若启用，设置smtp协议的SSL端口�?
	        email.setSubject(title);
	        email.setFrom(username); // 发件人地�?
	        email.setHtmlMsg(context);
	        
	        for (String to : toList) {
	        	email.addTo(to); // 收件人地�?，可以设置多�?
			}
	        // add the attachment
	        for (String fp : filepath) {
		    	 EmailAttachment attachment = new EmailAttachment();
			        attachment.setPath(fp);
			        attachment.setDisposition(EmailAttachment.ATTACHMENT);
			        attachment.setDescription("测试结果");
			        File f = new File(fp);
			        attachment.setName(f.getName());
			        email.attach(attachment);
			}
	        // send the email
	        email.send();
	    }

}
