package com.academy.model2app.blood.controller;
/*
 * 아래의 클래스는 MVC 패턴 중 Controller를 정의하는 것 
 * 
 */

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.academy.model2app.blood.model.BloodManager;

public class BloodController extends HttpServlet {
	BloodManager manager = new BloodManager();
	@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//Controller 역할
			//클라이언트가 선택한 파라미터를 분석하여  적절한결과 보여주기
		//1.요청을 받는다.
			request.setCharacterEncoding("utf-8");
			String blood = request.getParameter("blood");
			
			//3.알맞는 비지니스 로직 객체에게 일을 시킨다.
			String msg = manager.getAdvice(blood);
			
			//4.결과 페이지로 가져갈 것이 있을 경우 결과를 저장해 놓아야한다.
			HttpSession session = request.getSession();
			session.setAttribute("data", msg);
			
			//5.알맞는 뷰페이지 보여주기
			response.sendRedirect("/blood/advice.jsp");//클라이언트 브라우저로 하여금 지정한 url로 재접속 명령

		}
	
}
