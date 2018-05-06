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
    
    <title>My JSP 'adduser.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<%=path %>/css/houtai.css" rel="stylesheet" />
	<script type="text/javascript" src="<%=path %>/js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript">
		
	</script>

  </head>
  
  <body>
   <div id="tanchuk">
  <div class="tan_top"></div>
  <div class="tan_main">
  <form action="case/updatecase.do" method="post">
  	<input type="hidden" name="uuid" value="${case.uuid}">
	<table class="tck" width="213" border="0" cellspacing="0" cellpadding="0">
	   <tr bgcolor=white><td align=right>系&nbsp;&nbsp;统：</td><td>
		<select name="system">
			<c:forEach items="${systemnames}" var="d">
				<option value="${d.systemid}" ${case.system==d.systemid? "selected":""}>${d.systemname}</option>
			</c:forEach>
		</select>
		</td></tr>
		<tr bgcolor=white><td class="tc_w108" align=right>用例名称：</td><td>
		<input class="text_yhm" name="casename" value="${case.casename}" onblur="checkcasename(this.value)" maxlength="32"/><span id="checkname_error"></span>
		</td></tr>
		<tr bgcolor=white><td class="tc_w108" align=right>地址：</td><td>
			&nbsp;&nbsp;<textarea name="url" style="width:220px;">${case.url}</textarea>
		</td></tr>
		<tr bgcolor=white><td class="tc_w108" align=right>类型：</td><td>
		  <select name="type">
			 <option value="form" ${case.type=='form'? "selected":""}>form</option>
			 <option value="json" ${case.type=='json'? "selected":""}>json</option>
		</select>
		</td></tr>
		<tr bgcolor=white><td class="tc_w108" align=right>头部：</td><td>
			<input class="text_yhm" name="header" maxlength="32" value="${case.header}"/>
		</td></tr>
		<tr bgcolor=white><td class="tc_w108" align=right>参数：</td><td>
			<input class="text_yhm" name="params" maxlength="32" value="${case.params}"/>
		</td></tr>
		<tr bgcolor=white><td class="tc_w108" align=right>检查点：</td><td>
			&nbsp;&nbsp;<textarea name="checkpoint">${case.checkpoint}</textarea>
		</td></tr>
		<tr bgcolor=white><td class="tc_w108" align=right>关联：</td><td>
			&nbsp;&nbsp;<textarea name="correlation">${case.correlation}</textarea>
		</td></tr>
		<tr bgcolor=white><td class="tc_w108" align=right>开启：</td><td>
		  <select name="run">
			 <option value="Y" ${case.run=='Y'? "selected":""}>是</option>
			 <option value="N" ${case.run=='N'? "selected":""}>否</option>
		</select>
		</td></tr>
		<tr bgcolor=white><td colspan="2" align=center>
			<input type="submit" value="确定" class="queren"/><input type="button" value="取消" class="quxiao" onclick="hiden()"/>
		</td></tr>
	</table>
</form>
</div>
<div class="tan_bottom" style="position: absolute;"></div>
</div>
  </body>
</html>