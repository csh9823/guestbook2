<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.javaex.dao.GuestbookDao" %>
<%
	int no = Integer.parseInt(request.getParameter("no"));
	String password = request.getParameter("password");

	GuestbookDao guestbookDao = new GuestbookDao();
	guestbookDao.guestbookDelete(no, password);
	
	response.sendRedirect("./addList.jsp");
%>