package com.academy.model2app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//어떠한 종류의 컨트롤러이건 상관없이 모든 컨트롤러의 최상위 객체를 정의
//movie 이건 blood이건 같은 자료형화 한다.
//대신 어플리케이션의 모든 컨트롤러가 반드시 구현해야할 메서드를 강제시킨다.
public interface Controller {
	//업무를 처리하는 메서드를 execute()
	public void execute(HttpServletRequest request, HttpServletResponse response);
	//뷰페이지를 반환하는 메서드 getViewPage()
	public String getViewName();
	
	//포워딩해야 하지 말지를 결정
	public boolean isForward();
	
}
