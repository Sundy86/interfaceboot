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
        function check_systemname(a){
			if(a.length>0){
                checkflag1=true;
				$("#checkname_error").html("");
				$.post("system/checkname.do",{
					"systemname":a
				},function(data){
					if(data=="Y"){
						$("#checkname_error").html("<font color=red>该系统名称已存在！</font>");
                        checkflag2 = true;
					}else{
                        checkflag2 = false;
					}
				});
			}else{
				$("#checkname_error").html("<font color=red>请填写系统名称！</font>");
                checkflag1 = true;
			}
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
//        function check() {
//            if(checkflag1 && checkflag2 && checkflag3){
//                return true;
//            }else{
//                alert("数据填写不合法！");
//                return false;
//            }
//        }
        function check() {
            if(isduplicate){
                alert("请检查页面错误");
                return false;
            }
            return true;
        }
	</script>

  </head>
  
  <body>
   <div id="tanchuk">
  <div class="tan_top"></div>
  <div class="tan_main">
  <form action="system/addsystem.do" method="post" >
	<table class="tck" width="213" border="0" cellspacing="0" cellpadding="0">
		<tr bgcolor=white><td class="tc_w108" align=right>系统名称：</td><td>
		<input class="text_yhm" name="systemname" onblur="check_systemname(this.value)"/><span id="checkname_error"></span>
		</td></tr>
		
		<tr bgcolor=white><td class="tc_w108" align=right>描述：</td><td>
	      <input class="text_yhm" name="description" onblur="check_description(this.value)"/> <span id="description_error"></span>
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