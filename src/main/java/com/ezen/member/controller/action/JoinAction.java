package com.ezen.member.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.member.dao.MemberDao;
import com.ezen.member.dto.MemberDto;

public class JoinAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDao mdao = MemberDao.getInstance();
		MemberDto mdto = new MemberDto();
		mdto.setName(request.getParameter("name"));
		mdto.setUserid(request.getParameter("userid"));
		mdto.setPwd(request.getParameter("pwd"));
		mdto.setEmail(request.getParameter("email"));
		mdto.setPhone(request.getParameter("phone"));
		mdto.setAdmin(Integer.parseInt(request.getParameter("admin")));
		
		int result = mdao.insertMember(mdto);
		if(result==1) request.setAttribute("message", "회원가입 완료. 로그인하세요.");
		else request.setAttribute("message", "회원가입 실패. 관리자에게 문의하세요.");
		
		//RequestDispatcher dp = request.getRequestDispatcher("member/loginForm.jsp");
		//dp.forward(request, response);
		
		//리퀘스트 포워딩으로 insert하고 이동한 경우, 새로고침(f5)를 누르면 request에 담겨있는 같은 값으로
		//insert하는 동작이 다시 동작합니다. 그래서 기본키 또는 unique 값 제약조건에 위배되는 경우가 많습니다.
		//따라서 insert명령에 한해서만 response.sendRedirect로 이동합니다.
		response.sendRedirect("member/loginForm.jsp");
	}

}
