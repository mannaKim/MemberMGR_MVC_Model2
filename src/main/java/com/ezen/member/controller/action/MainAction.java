package com.ezen.member.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezen.member.dao.MemberDao;
import com.ezen.member.dto.MemberDto;

public class MainAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "member/main.jsp";
		
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser")==null)
			url="member/loginForm.jsp";
		
		MemberDao mdao = MemberDao.getInstance();
		ArrayList<MemberDto> list = mdao.selectMember();
		
		request.setAttribute("mList", list);
		
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
