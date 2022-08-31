package com.academy.model2app.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.academy.model2app.blood.controller.BloodController;
import com.academy.model2app.blood.model.BloodManager;
import com.academy.model2app.movie.controller.MovieController;
import com.academy.model2app.movie.model.MovieManager;

/*
 * 모든 요청마다 1:1 대응하는 컨트롤러를 전면에 내세우면 오히려 유지 보수성이 떨어진다.
 */
public class DispatcherServlet extends HttpServlet{
	

	//모든 요청을 이 서블릿이 받아야한다.
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doRequest(req, resp);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doRequest(req, resp);
	}
	//클라이언트의 요청이 어떤 방식이던 상관 없이 요청을 받기위해 공통 메소드에서 컨트롤러의 요청을 처리
	protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("요청을 받았습니다.");
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		System.out.println(uri);
		Controller controller = null;
		//2)요청 분석
		if(uri.equals("/movie.do")) {	//영화에 대한 요청인지
			//영화 전담자인 MovieController 에게 업무를 맡긴다.
			controller = new BloodController();
			
		}else if(uri.equals("/blood.do")) {	//혈액형에 대한 요청인지
			
		}
		controller.execute(request, response);
		//5)알맞은 뷰 페이지
		RequestDispatcher dis = request.getRequestDispatcher(controller.getViewPage());
		dis.forward(request, response);
		

	}
	
	
}
