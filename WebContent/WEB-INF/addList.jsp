<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.javaex.vo.GuestbookVo" %>
<%@ page import="com.javaex.dao.GuestbookDao" %>
<%
	List<GuestbookVo> guestbookList = (List<GuestbookVo>)request.getAttribute("gList");	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 등록폼영역 -->
	<form action="/guestbook2/gbc" method="get">
		<table border="1" width="500px">
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" value=""></td>
				<td>비밀번호</td>
				<td>
					<input type="password" name="password" value="">
					<input type="hidden" name="action" value="add">
				</td>
			</tr>
			<tr>
				<td colspan="4"><textarea name="content" cols="65" rows="5"></textarea></td>
			</tr>
			<tr>
				<td colspan="4">
					<button type="submit">글작성</button>
				</td>
			</tr>	
		</table>
	</form>
	<!-- 등록폼영역 -->
	<br>
	<!-- 리스트영역 -->
	<% 
	for(int i=0; i<guestbookList.size(); i++){ 
	%>
		<table border="1" width="500px">
		<tr>
			<td><%= guestbookList.get(i).getNo() %></td>
			<td><%= guestbookList.get(i).getName() %></td>
			<td><%= guestbookList.get(i).getRegDate() %></td>
			<td><a href="/guestbook2/gbc?action=deleteForm&no=<%= guestbookList.get(i).getNo() %>">삭제</a></td>
		</tr>
		<tr>
			<td colspan="4"><%= guestbookList.get(i).getContent() %>
			</td>
		</tr>
		</table>
		<br>
	<% 
	} 
	%>
	<!-- 리스트영역 -->
</body>
</html>