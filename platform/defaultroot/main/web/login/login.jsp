<%@page import="com.config.Config"%>
<%@ page language="java" contentType="text/html; charset=GB18030"
	pageEncoding="GB18030"%>
<%@ include file="/hander.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>platform-login</title>
<link rel="stylesheet" type="text/css" href="login.css" />
<script type="text/javascript" src="_js/login.js"></script>
</head>
<body class="bg">
	<div class="login_content">
		<span class="sys_name"><%=Config.get("app.sys_hm_name")%></span>
		<div class="content_mid">
			<ul id="login_form">
				<li><span> userName: </span> <input name="userName" /></li>
				<li><span> userPass: </span> <input type="password" name="userPass" /></li>
			</ul>
		</div>
		<div class="content_bottom">
			<div id="login_button" class="login_button"></div>
		</div>
	</div>
	<script>
		$(document).login(
			{
				'mainUrl':'<%=request.getContextPath()%><%=Config.get("app.main_url")%>'
			}
		);
	</script>
</body>
</html>