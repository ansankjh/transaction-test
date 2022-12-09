<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "vo.*" %>
<%
	// 유효성 검사
	// 로그인이 되어있지 않다면 로그인페이지
	if(session.getAttribute("loginMember") == null) {
		response.sendRedirect(request.getContextPath()+"/loginForm.jsp");
		return;
	}
	// 로그인 되어있다면 session 정보를 가져온다.
	// Object obj = session.getAttribute("loginMember");
	// Member loginMember = (Member)obj;
	Member loginMember = (Member)session.getAttribute("loginMember");
	
%>
<!-- View 있다 -->
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Main</title>
	</head>
	<body>
		<h1><%=loginMember.getMemberName()%>님</h1>
		<div>
			<a href="<%=request.getContextPath()%>/logout.jsp">로그아웃</a>
			<a href="<%=request.getContextPath()%>/deleteMember.jsp">회원탈퇴</a>
		</div>
	</body>
</html>