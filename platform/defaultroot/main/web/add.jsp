1<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@ include file="/auth_hander.jsp" %>
<%@ taglib uri="/test_tag" prefix="test"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>platform-add</title>
</head>
<body>
	<form action="${contextPath}/add.do?objectName=UserInfo&objectEvent=Add" method="post">
		<p>
		userName:<input type="text" name="userName" value="test1"/>
		</p>
		<p>
		userPass:<input type="text" name="userPass" value="123"/>
		</p>
		<p>
		hmName:<input type="text" name="hmName" value="test1"/>
		</p>
		<p>
		showName:<input type="text" name="showName" value="test1"/>
		</p>
		<p>
		idcard:<input type="text" name="idNum" value="1"/>
		</p>
		<p>
		ipAddr:<input type="text" name="ipAddr" value="1"/>
		</p>
		<p>
		macAddr:<input type="text" name="macAddr" value="1"/>
		</p>
		<p>
		userAvailId:<input type="text" name="userAvailId" value="1"/>
		</p>
		<input type="submit" />
	</form>
	${message }
</body>
</html>