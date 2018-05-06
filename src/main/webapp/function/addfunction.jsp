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
        var checkflag1=false;
        var checkflag2=false;
        function check_funcname(name){
		    if(name.length>0){
                checkflag1=true;
		        $("#checkname_error").html("");
		        $.post("/function/checkname.do",{"funcname":name},function (data){
		            if(data=="Y"||data=="\"Y\""){
                        $("#checkname_error").html("<font color=red>该菜单已存在！</font>");
                        checkflag2=true;
					}else{
                        checkflag2=false;
					}
				})
			}
			else{
                $("#checkname_error").html("<font color=red>请填写菜单名称！</font>");
                checkflag1=true;
			}
		}
        function check(){
            if(checkflag1&&checkflag2){
                return true;
            }else{
                alert("数据填写不合法！");
                return false;
            }
        }

		
	</script>

  </head>
  
  <body>
   <div id="tanchuk">
  <div class="tan_top"></div>
  <div class="tan_main">
  <form action="function/addfunction.do" method="post" onsubmit="return check()">
	<table class="tck" width="213" border="0" cellspacing="0" cellpadding="0">
		<tr bgcolor=white><td class="tc_w108" align=right>菜单名称：</td><td>
		<input class="text_yhm" name="funcname" onblur="check_funcname(this.value)"/><span id="checkname_error"></span>
		</td></tr>
		<tr bgcolor=white><td class="tc_w108" align=right>父级菜单：</td><td>
			<select name="parentid">
				<option value="0">父菜单</option>
				<c:forEach items="${functions}" var="o">
					<option value="${o.funcid }">${o.funcname }</option>
				</c:forEach>
			</select>
		</td></tr>
		<tr bgcolor=white><td class="tc_w108" align=right>url：</td><td>
			<input class="text_yhm" name="url"/>
		</td></tr>
		<tr bgcolor=white><td class="tc_w108" align=right>顺序：</td><td>
			<input class="text_yhm" name="funcOrder"/>
		</td></tr>
		<tr bgcolor=white><td colspan="2" align=center>
			<input type="submit" value="确定" class="queren"/>
			<input type="button" value="取消" class="quxiao" onclick="hiden()"/>
		</td></tr>
	</table>
</form>
</div>
<div class="tan_bottom" style="position: absolute;"></div>
</div>
  </body>
</html>