<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String msg = request.getParameter("msg");
	if(session.getAttribute("loginMember") != null) {
		response.sendRedirect(request.getContextPath()+"/main.jsp");
		return;
	}
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>insertMemberForm</title>
	</head>
	<body>
		<h1>회원가입</h1>
		<%
			if(msg != null) {
		%>
				<%=msg%>
		<%
			}
		%>
		<div>
			<form action="<%=request.getContextPath()%>/insertMemberAction.jsp" method="post">
				<div>
					<table border="1">
						<tr>
							<td>ID</td>
							<td>
								<input type="text" name="memberId">
							</td>
						</tr>
						<tr>
							<td>PW</td>
							<td>
								<input type="password" name="memberPw">
							</td>
						</tr>
						<tr>
							<td>CheckPW</td>
							<td>
								<input type="password" name="memberPw2">
							</td>
						</tr>
						<tr>
							<td>NAME</td>
							<td>
								<input type="text" name="memberName">
							</td>
						</tr>
					</table>
				</div>
				<div>
					<button type="submit">회원가입</button>
				</div>
			</form>
		</div>
	</body>
</html>