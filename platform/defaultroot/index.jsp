<%@page import="com.config.Config"%>
<%@ page language="java" contentType="text/html; charset=GB18030"
	pageEncoding="GB18030"%>
<%-- <%session.setAttribute("userContext", new String()); %> --%>
<% response.sendRedirect(request.getContextPath()+Config.get("app.main_url"));%>