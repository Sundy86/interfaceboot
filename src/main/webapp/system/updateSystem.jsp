<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'updateSystem.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   <div id="tanchuk">
  <div class="tan_top"></div>
  <div class="tan_main">
  <form action="system/updatesystem.do" method="post">
  <input type="hidden" name="systemid" value="${system.systemid }">
	<table class="tck" width="213" border="0" cellspacing="0" cellpadding="0">
		<tr bgcolor=white><td class="tc_w108" align=right>名称：</td><td>
		<input class="text_yhm" name="systemname" value="${system.systemname }" onblur="check_systemname(this.value)"/><span id="checkname_error"></span>
		</td></tr>
		
		<tr bgcolor=white><td class="tc_w108" align=right>描述：</td><td>
			<input class="text_yhm" name="description" value="${system.description}" onblur="check_description(this.value)"/><span id="description_error"></span>
		</td></tr>
		<tr bgcolor=white><td colspan="2" align=center>
			<input type="submit" value="确定" class="queren"/><input type="button" value="取消" class="quxiao" onclick="hiden()"/>
		</td></tr>
	</table>
</form>
</div>
<div class="tan_bottom"></div>
</div>
  </body>
</html>