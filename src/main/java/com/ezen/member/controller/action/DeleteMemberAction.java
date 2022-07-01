package com.ezen.member.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezen.member.dao.MemberDao;
import com.ezen.member.dto.MemberDto;

public class DeleteMemberAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 유저 조회
		HttpSession session = request.getSession();
		MemberDto mdto = (MemberDto)session.getAttribute("loginUser");
		
		//조회된 아이디로 멤버 삭제
		MemberDao mdao = MemberDao.getInstance();
		mdao.deleteMember(mdto.getUserid());
		
		//세션값 삭제
		session.invalidate();
		
		request.setAttribute("message", mdto.getUserid()+"회원 탈퇴가 정상 진행되었습니다.");
		
		RequestDispatcher dp = request.getRequestDispatcher("member.do?command=loginForm");
		dp.forward(request, response);
	}

}
