<html>
<body>
<h3><font color="red">接口有错误！</font></h3>
<h6>Detail:</h6>
<div>
    <p>测试时间:${runtime?datetime}</p>
	<#setting classic_compatible=true>
	<#list reportlist as report>
			<font color="red"> ${report.caseName}</font><br>
			${report.url}<br>
			错误原因:${report.msg}<br>
			<hr>
	</#list>
</div>
<span>Sent using FreeMarker Template</span>
</body>
</html>