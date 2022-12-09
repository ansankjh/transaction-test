<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String msg = request.getParameter("msg");
	// 유효성 검사 : 이 페이지 안에 있는 코드를 계속해서 실행 해야 하는지
	// 로그인 되어있다면 이 페이지는 실행x
	if(session.getAttribute("loginMember") != null) {
		// 이미 로그인 되어 있으니 웹브라우저에서 다시 main.jsp 재요청하세요
		response.sendRedirect(request.getContextPath()+"/main.jsp");
		return;
	}
%>
<!-- View -->
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>loginForm</title>
	</head>
	<body>
		<h1>로그인</h1>
		<%
			if(msg != null) {
		%>
				<%=msg%>
		<%
			}
		%>
		<div>
			<form action="<%=request.getContextPath()%>/loginAction.jsp" method="post">
				<table border="1">
					<tr>
						<td>ID</td>
						<td><input type="text" name="memberId"></td>
					</tr>
					<tr>
						<td>PW</td>
						<td><input type="password" name="memberPw"></td>
					</tr>
				</table>
				<div>
					<button type="submit">로그인</button>
					<a href="<%=request.getContextPath()%>/insertMemberForm.jsp">회원가입</a>
				</div>											
			</form>
		</div>
	</body>
</html>