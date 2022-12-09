<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "vo.*" %>
<%@ page import = "service.*" %>
<%@ page import = "java.net.*" %>
<%
	// 비로그인시 접근불가
	if(session.getAttribute("loginMember") == null) {
		response.sendRedirect(request.getContextPath()+"/loginForm.jsp");
		return;
	}

	Member loginMember = (Member)session.getAttribute("loginMember");
			
	MemberService memberService = new MemberService();
	memberService.deleteMember(loginMember.getMemberId());
	
	session.invalidate();
	String msg = URLEncoder.encode("회원탈퇴", "utf-8");
	response.sendRedirect(request.getContextPath()+"/loginForm.jsp?msg="+msg);
%>