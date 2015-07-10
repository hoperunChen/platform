<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@ include file="/auth_hander.jsp" %>
<%@ taglib uri="/test_tag" prefix="test"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>platform-list</title>
</head>
<body>
	<c:forEach items="${pager.getAll()}" var="obj">
		${obj } <a id="delete_${obj.get('sid')}" href="${contextPath}/remove.do?objectName=UserInfo&objectEvent=Remove&sid=${obj.get('sid')}">删除</a>
	</c:forEach>
	<br/>
</body>
</html>