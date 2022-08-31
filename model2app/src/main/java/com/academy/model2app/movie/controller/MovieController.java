package com.academy.model2app.movie.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
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
		/*

		세션을 이용하면 브라우저를 닫지 않는 한 세션이 유지되어 데이터를 보관할 수 있지만,
		세션 방법 이외에 방법이 있다면 안할 이유가 없다
		현실의 114, 이메일포워딩 처럼 javaEE엣도 서버측의 특정 자원으로 현재 요청을 포워딩하는 기술을 지원며
		사용되는  객체가RequestDispatcher 이다.
		*/
		request.setAttribute("data", msg);//request는 요청이 끊기면 사라지지만, 요청이 끊기지 않으면 살아있다.
		RequestDispatcher dis = request.getRequestDispatcher("/movie/result.jsp");//포워딩 객체
		dis.forward(request, response);//포워딩 실행 
		
	}
}
