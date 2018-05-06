<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript" src="<%=path %>/js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript">
	DD_belatedPNG.fix('div, ul, img, li, input,p,ul,ol,h1,h2,h3,a,span,i'); 
		var aa=false,bb=false,dd=false,ee=false,ff=false,gg=false,ee=false,hh=false,kk=false;
		function checkusername(a){
			if(a.length>0){
				aa=true;
				$("#username_error").html("");
				$.post("user/checkname.do",{
					"username":a
				},function(data){
					if(data=="Y"){
						bb=true;
					}else{
						$("#username_error").html("<font color=red>用户名已存在！</font>");
						bb=false;
					}
				});
			}else{
				$("#username_error").html("<font color=red>请填写用户名！</font>");
				aa=false;
			}
		}
		function checkpassword(e){
			if(e.length>0){
				$("#password_error").html("");
				hh=true;
			}else{
				$("#password_error").html("<font color=red>请填写密码！</font>");
				hh=false;
			}
   		 }
		function checkbianhao(e){
			if(e.length>0){
				$("#bianhao_error").html("");
				ee=true;
			}else{
				$("#bianhao_error").html("<font color=red>请填写工号！</font>");
				ee=false;		
			}
		}
		function checkname(e){
			if(e.length>0){
				$("#name_error").html("");
				dd=true;
			}else{
				$("#name_error").html("<font color=red>请填写姓名！</font>");
				dd=false;
			}
		}
		function checksex(e){
			if(e.length>0){
				$("#usersex_error").html("");
				kk=true;
			}else{
				$("#usersex_error").html("<font color=red>请填写性别！</font>");
				kk=false;
			}
		}
		
		function checkaddr(e){
			if(e.length>0){
				$("#useraddr_error").html("");
				ff=true;
			}else{
				$("#useraddr_error").html("<font color=red>请填写地址！</font>");
				ff=false;
			}
		}
		function checkdescription(e){
			if(e.length>0){
				$("#description_error").html("");
				gg=true;
			}else{
				$("#description_error").html("<font color=red>请填写描述！</font>");
				gg=false;
			}
		}

		function ccc(){
			if(aa&&bb&&dd&&ee&&gg&&ff&&kk&&hh&&ff){
				return true;
			}else{
				alert("数据填写不合法！");
				return false;
			}
		}
	</script>
<div id="tanchuk">
  <div class="tan_top"></div>
  <div class="tan_main">
  <form action="/user/updateuser.do" method="post">
  	<input type="hidden" name="userid" value="${user.userid }">
	<table class="tck" width="213" border="0" cellspacing="0" cellpadding="0">
		<tr bgcolor=white><td align=right>工&nbsp;&nbsp;号：</td><td>
		<input name="bianhao" class="text_yhm" value="${user.bianhao }" onblur="checkbianhao(this.value)"/><span id="bianhao_error"></span>
		</td></tr>
		<tr bgcolor=white><td align=right class="tc_w108">用户名：</td><td>
		<input class="text_yhm" name="username" value="${user.username }" onblur="checkusername(this.value)" /><span id="username_error"></span>
		</td></tr>
		<tr bgcolor=white><td align=right class="tc_w108">密&nbsp;&nbsp;码：</td><td>
		<input class="text_yhm" name="password" value="${user.password }" onblur="checkpassword(this.value)" /><span id="password_error"></span>
		</td></tr>
		<tr bgcolor=white><td align=right>姓&nbsp;&nbsp;名：</td><td>
		<input name="name" class="text_yhm" value="${user.name }" onblur="checkname(this.value)"/><span id="name_error"></span>
		</td></tr>
		<tr bgcolor=white><td align=right>性&nbsp;&nbsp;别：</td><td>
		<input name="sex" class="text_yhm" value="${user.sex }" onblur="checksex(this.value)"/><span id="usersex_error"></span>
		</td></tr>
		<tr bgcolor=white><td align=right>地&nbsp;&nbsp;址：</td><td>
		<input name="addr" class="text_yhm" value="${user.addr }" onblur="checkaddr(this.value)"/><span id="useraddr_error"></span>
		</td></tr>
		<tr bgcolor=white><td align=right>描&nbsp;&nbsp;述：</td><td>
		<input name="description" class="text_yhm" value="${user.description }" onblur="checkdescription(this.value)"/><span id="description_error"></span>
		</td></tr>
		
		<tr bgcolor=white><td align=right>角&nbsp;&nbsp;色：</td><td>
		<select name="roleid" >
			<c:forEach items="${rolenames}" var="r">
				<option value="${r.roleid }" ${r.roleid==user.roleid?"selected":"" }>${r.rolename }</option>
			</c:forEach>
		</select>
		</td></tr>
		<tr bgcolor=white><td colspan="2" align=center>
			<input type="submit" value="确定" class="queren"/><input type="button" value="取消" class="quxiao" onclick="hiden()"/>
		</td></tr>
	</table>
</form>
</div>
<div class="tan_bottom"></div>
</div>
