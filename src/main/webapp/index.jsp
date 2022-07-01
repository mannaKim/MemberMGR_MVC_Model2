<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index.jsp</title>
</head>
<body>
	<%
		response.sendRedirect("member.do?command=loginForm");
		//member.do : 현재 프로젝트에 있는 유일한 서블릿의 이름(URL mapping)
		//유일한 서블릿을 호출하고 지금 하고자하는 작업의 제목을 command라는 파라미터로 보내서
		//해당 기능을 다른 기능과 구분하여 실행되게 합니다.
	%>
</body>
</html>