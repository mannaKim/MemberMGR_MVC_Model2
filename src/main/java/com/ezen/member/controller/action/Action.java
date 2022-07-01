package com.ezen.member.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	//인터페이스에서는 추상메서드(abstract)만 만들 수 있음
	//그리고 abstract 키워드는 생략해도 됨(default이기 때문에)
	
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;
	
}
