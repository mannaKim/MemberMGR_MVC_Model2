package com.ezen.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.member.controller.action.Action;
//import com.ezen.member.controller.action.JoinFormAction;
//import com.ezen.member.controller.action.LogOutAction;
//import com.ezen.member.controller.action.LoginFormAction;

/**
 * Servlet implementation class MemberServlet
 */
@WebServlet("/member.do")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		//같이 전달된 command 파라미터를 getParameter 메서드로 받습니다.
		String command = request.getParameter("command");
		//command : "loginForm"
		
		/* 방법#1 : 각 클래스들의 변수를 생성하기 위해 클래스들을 import해야 함
		if(command.equals("loginForm")) {
			LoginFormAction lfa = new LoginFormAction();
			lfa.execute(request, response);
		}else if(command.equals("logout")) {
			LogOutAction loa = new LogOutAction();
			loa.execute(request, response);
		}else if(command.equals("joinForm")) {
			
		}
		*/

		Action ac = null;
		/* 방법#2
		if(command.equals("loginForm")) ac = new LoginFormAction();
		else if(command.equals("logout")) ac = new LogOutAction();
		else if(command.equals("joinForm")) ac = new JoinFormAction();
		*/
		
		//방법#3 : ActionFactory는 같은 패키지에 있어서 import하지 않아도 됨
		ActionFactory af = ActionFactory.getInstance();
		ac = af.getAction(command); 
		
		if(ac!=null) ac.execute(request, response);
		else System.out.println("command : "+command+" - command 값을 확인하세요");
		
		//command에 전달된 값에 따라 어떤 작업을 할지 결정되어 실행됩니다.
		
		//각 command 별 실행할 작업들은 해당 실행코드가 들어있는 클래스 내부, 
		//그 중에서도 execute 메서드 안에 있습니다.
		//Model2방식은 각 기능별로 클래스가 제작되어 실행되기를 기다리고,
		//command 값에 따라 선택되어 실행되는 형식입니다.
		
		//각 기능이 내장된 객체들의 인스턴스들은 클래스들이 상속(implements)받은 부모 인터페이스(Action)의
		//레퍼런스 변수(여기선 'ac'라고 선언함)에 저장하고, 레퍼런스변수명.execute()로 실행합니다.
		
		//각 클래스에 있는 execute 메서드는 Action 인터페이스에 존재하는 추상메서드를 오버라이딩한 메서드입니다.
		//각 클래스가 Action 인터페이스를 상속(implements)하여, execute 메서드가 오버라이딩 되면
		//Action 인터페이스의 레퍼런스 변수로 자식 클래스의 execute 메서드를 호출하여 사용합니다.
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

}
