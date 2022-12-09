<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--  View가 없다 -->
<%
	session.invalidate(); // 현재 세션을 폐기하고 새로운 세션부여받음
	//->세션정보 사라진다+> 로그아웃
	response.sendRedirect(request.getContextPath()+"/loginForm.jsp");
%>	