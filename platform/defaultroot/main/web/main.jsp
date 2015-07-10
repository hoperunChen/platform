<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@ include file="/auth_hander.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>platform-main</title>
</head>
<body>
	contextPath:${contextPath }
	<br/>
	userContext:${userContext }
	<br/>
	<a href="${contextPath }/list.do?objectName=UserInfo&objectEvent=Query">list</a>
	<br/>
	<a href="${contextPath }/view.do?objectName=UserInfo&objectEvent=View&sid=1">view</a>
	<br />
	<a href="${contextPath}/main/web/add.jsp?objectName=UserInfo&objectEvent=PreAdd" >add</a>
	<br />
	<a href="${contextPath}/main/web/update.jsp?objectName=UserInfo&objectEvent=PreUpdate&sid=13" >update</a>
</body>
</html>