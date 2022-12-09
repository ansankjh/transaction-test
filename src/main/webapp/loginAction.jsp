<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "vo.*" %>
<%@ page import = "service.*" %>
<%@ page import = "java.net.*" %>
<%
	if(session.getAttribute("loginMember") != null) {
		response.sendRedirect(request.getContextPath()+"/main.jsp");
		return;
	}
	// Controller
	String memberId = request.getParameter("memberId");
	String memberPw = request.getParameter("memberPw");
	
	// System.out.println(memberId);
	// System.out.println(memberPw);
	
	// 메서드 호출에 필요한 객체 
	Member paramMember = new Member();
	paramMember.setMemberId(memberId);
	paramMember.setMemberPw(memberPw);
	
	
	// MemberDao memberDao = new MemberDao();
	// Member member = memberDao.login(paramMember);
	
	MemberService memberService = new MemberService();
	Member member = memberService.login(paramMember);
	
	// resultMember가 null이 아니면 세션에 resultMember 저장, main.jsp로 
	if(member == null) {
		String msg = URLEncoder.encode("로그인 실패", "utf-8");
		response.sendRedirect(request.getContextPath()+"/loginForm.jsp?msg="+msg);
	} else {
		session.setAttribute("loginMember", member);
		response.sendRedirect(request.getContextPath()+"/main.jsp");
	}
%>
<!-- View 없다 -->