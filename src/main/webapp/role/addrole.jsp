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
        var checkflag3=false;
        function check_rolename(name){
            if(name.length>0){
                checkflag1=true;
                $("#checkname_error").html("");
                $.post("/role/checkname.do",{"rolename":name},function (data){
                    if(data=="Y"||data=="\"Y\""){
                        $("#checkname_error").html("<font color=red>该角色已存在！</font>");
                        checkflag2=true;
                    }else{
                        checkflag2=false;
                    }
                })
            }
            else{
                $("#checkname_error").html("<font color=red>请填写角色名称！</font>");
                checkflag1=true;
            }
        }

		function check_parentid(a){
			document.getElementById(a).checked=true;
		}

		function check_description(e){
			if(e.length>0){
				$("#description_error").html("");
                checkflag3=true;
			}else{
				$("#description_error").html("<font color=red>请填写角色描述！</font>");
                checkflag3=false;
			}
		}
		
		function check(){
			if(checkflag1 && checkflag2 && checkflag3){
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
  <form name="form1" method="post" action="role/addrole.do" onsubmit="return check()">
	<table class="tck" width="213" border="0" cellspacing="0" cellpadding="0">
		<tr bgcolor="white"><td class="tc_w108" align=right>角色名：</td><td>
		<input class="text_yhm" name="rolename" id="rolename" onblur="check_rolename(this.value)"/><span id="checkname_error"></span>
		</td></tr>
		<tr bgcolor=white><td class="tc_w108" align=right>描&nbsp;&nbsp;述：</td><td>
		<input class="text_yhm" name="description" onblur="check_description(this.value)"/><span id="description_error"></span>
		</td></tr>
		<tr bgcolor=white><td class="tc_w108" align=right>权限类型：</td><td>
		<select name="status">
			<option value="super">超级管理员</option>
			<option value="1">一般管理员</option>
			<option value="2">普通用户</option>
		</select>
		</td></tr>
		<tr bgcolor=white><td align=right>权&nbsp;&nbsp;限：</td><td align=left>
		<c:forEach items="${objs}" var="o">
				<c:if test="${o.parentid=='0'}">
					<input type="checkbox" id="${o.funcid }" name="aaa" value="${o.funcid }" />
					<b>${o.funcname }</b><br>
						<c:forEach items="${roles}" var="e">
		   						<c:if test="${e.parentid==o.funcid && (e.url==null || e.url=='')}">
									 &nbsp;<input type="checkbox" id="${e.funcid }" name="aaa" value="${e.funcid }"
									 onclick="check_parentid('${e.parentid }')" />
										<b>${e.funcname }</b><br>
										<c:forEach items="${roles}" var="a">
			 								<c:if test="${a.parentid==e.funcid && (a.url==null || a.url=='')}">
												&nbsp;<input type="checkbox" id="${a.funcid }" name="aaa" value="${a.funcid }"
									 			onclick="check_parentid('${a.parentid }')" />
												${o.funcname }
											</c:if>
											<c:if test="${a.parentid==e.funcid && a.url!=null && a.url!=''}">
												&nbsp;&nbsp;<input type="checkbox" id="${a.funcid }" onclick="check_parentid('${a.parentid }')" name="aaa" value="${a.funcid }" />
												${a.funcname }<br>
											</c:if>
			   							</c:forEach>
								</c:if>
								<c:if test="${e.url!=null && e.url!=''}">
									<c:if test="${e.parentid==o.funcid && e.url!=null}">
									&nbsp;&nbsp;<input type="checkbox" id="${e.funcid }" onclick="check_parentid('${e.parentid }')" name="aaa" value="${e.funcid }" />
									${e.funcname }<br>
									</c:if>
								</c:if>
	   					</c:forEach>
				</c:if>
		</c:forEach>
		</td></tr>
		<tr bgcolor="white"><td colspan="2" align="center">
			<input type="submit" value="确定" class="queren"/>
            <input type="button" value="取消" class="quxiao" onclick="hiden()"/>
		</td></tr>
	</table>
</form>
</div>
<div class="tan_bottom"></div>
</div>
  </body>
</html>