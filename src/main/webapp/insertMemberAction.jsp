<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "vo.*" %>
<%@ page import = "service.*" %>
<%@ page import = "java.net.*" %>
<%
	//Controller
	if(session.getAttribute("loginMember") != null) {
		response.sendRedirect(request.getContextPath()+"/main.jsp");
		return;
	}
	
	request.setCharacterEncoding("utf-8");
	String memberId = request.getParameter("memberId");
	String memberPw = request.getParameter("memberPw");
	String memberPw2 = request.getParameter("memberPw2");
	String memberName = request.getParameter("memberName");
	
	if(!request.getParameter("memberPw").equals(request.getParameter("memberPw2"))) {
		String msg = URLEncoder.encode("pw와pw2가 다릅니다.", "utf-8");
		response.sendRedirect(request.getContextPath()+"/insertMemberForm.jsp?msg="+msg);
		return;
	}
	
	/*
	System.out.println(memberId);
	System.out.println(memberPw);
	System.out.println(memberPw2);
	System.out.println(memberName);
	*/
	
	// 회원가입 메서드 호출시 매개값
	Member member = new Member();
	member.setMemberId(memberId);
	member.setMemberPw(memberPw);
	member.setMemberName(memberName);
	
	// Model
	//MemberDao memberDao = new MemberDao();
	MemberService memberService = new MemberService();
	// 아이디 중복방지
	if(memberService.checkMemberId(memberId) == false) {
		String msg = URLEncoder.encode("아이디 중복", "utf-8");
		response.sendRedirect(request.getContextPath()+"/insertMemberForm.jsp?msg="+msg);
	} else {
		int insert = memberService.insertMember(member);
		String msg = URLEncoder.encode("회원가입완료", "utf-8");
		response.sendRedirect(request.getContextPath()+"/loginForm.jsp?msg="+msg);
	}
%>



