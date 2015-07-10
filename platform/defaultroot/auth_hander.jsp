<%@page import="com.config.Config"%>
<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%
	if(session.getAttribute("userContext")==null)
		 response.sendRedirect(request.getContextPath()+Config.get("app.login_url"));
%>
<%@ include file="/hander.jsp"%>