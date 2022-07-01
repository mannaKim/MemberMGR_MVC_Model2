package com.ezen.member.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.member.dao.MemberDao;
import com.ezen.member.dto.MemberDto;

public class IdCheckAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		
		MemberDao mdao = MemberDao.getInstance();
		MemberDto mdto = mdao.getMember(userid);
		int result = 1;
		if(mdto == null) result = -1;
		
		request.setAttribute("result",result);
		request.setAttribute("userid",userid);
		
		RequestDispatcher rd = request.getRequestDispatcher("member/idcheck.jsp");
		rd.forward(request, response);
	}

}
