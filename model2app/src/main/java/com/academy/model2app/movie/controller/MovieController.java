package com.academy.model2app.movie.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.academy.model2app.movie.model.MovieManager;

public class MovieController extends HttpServlet{
	MovieManager manager = new MovieManager();
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1)요청을 받는다.
		 * 2)요청을 분석
		 * 3)안맞은 로직객체에게 일 시킨다.
		 * 4)결과페이지로 전달할 것이 있다면 결과저장
		 * 5)결과 보여주기
		 */
		request.setCharacterEncoding("utf-8");
		String movie = request.getParameter("movie");
		
		//3단계
		String msg = manager.getAdvice(movie);
		
		//4단계 결과 저장
		HttpSession session = request.getSession();
		session.setAttribute("data", msg);
		
		//5단계
		response.sendRedirect("/movie/result.jsp");//클라이언트 브라우저로 하여금 지정한 url로 재접속 명령
	}
}
