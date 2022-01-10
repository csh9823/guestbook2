<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.javaex.vo.GuestbookVo" %>
<%
	int no = (int)request.getAttribute("no");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<form action="/guestbook2/gbc" method="get">
			<input type="hidden" name="no" value="<%= no %>">
			비밀번호:<input type="password" name="password" value=""> <br>
			<input type="text" name="action" value="delete"> <br>
		<button type="submit">확인</button>
		
		</form>
		<p>
			<a href="./addList.jsp">메인으로 돌아가기</a>
		</p>
	</body>
</html>