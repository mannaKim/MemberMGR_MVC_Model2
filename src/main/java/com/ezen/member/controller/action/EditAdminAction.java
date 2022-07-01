package com.ezen.member.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezen.member.dao.MemberDao;
import com.ezen.member.dto.MemberDto;

public class EditAdminAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//전달된 userid로 회원을 검색하여 리턴받은 후
		String userid=request.getParameter("userid");
		MemberDao mdao = MemberDao.getInstance();
		MemberDto mdto = mdao.getMember(userid);
		
		//해당 회원의 admin값을 반대값으로 수정하는 메서드 호출
		int admin = mdto.getAdmin();
		if(admin==0) admin=1;
		else admin=0;
		mdao.editAdmin(userid,admin);
		
		//main으로 돌아감
		RequestDispatcher dp = request.getRequestDispatcher("member.do?command=main");
		dp.forward(request, response);
	}

}
