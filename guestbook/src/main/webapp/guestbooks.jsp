<%@page import="org.edwith.webbe.guestbook.dto.Guestbook"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="org.edwith.webbe.guestbook.dao.*"%>
<%@ page import="org.edwith.webbe.guestbook.dto.*"%>
<%@ page import="org.edwith.webbe.guestbook.servlet.*"%>
<%@page import="java.util.List"%>

<html>
<head>
<title>방명록</title>
</head>
<body>
	
	<table border="1">

		<tr>
			<th>번호</th>
			<th>이름</th>
			<th>내용</th>
			<th>날짜</th>

		</tr>

		<%
			GuestbookDao guestbookDao = new GuestbookDao();
			List<Guestbook> list = guestbookDao.getGuestbooks();
			for (Guestbook guestbook : list) {
		%>
		<tr>
			<td><%=guestbook.getId()%></td>
			<td><%=guestbook.getName()%></td>
			<td><%=guestbook.getContent()%></td>
			<td><%=guestbook.getRegdate()%></td>

		</tr>
		<%
			}
		%>
	</table>

	<br>
	<br>
	<br>

	<form method="post" action="guestbooks/write">
		번호 : <input type="text" name="id"><br>
		이름 : <input type="text" name="name"><br> 내용 :
		<textarea name="content" cols="50" rows="5"></textarea><br><br>
		날짜 : <input type="text" name="regdate">
		<br> <input type="submit" value="확인">
	</form>
</body>
</html>
