package com.academy.model2app.movie.controller;
/*
 * 영화에 대한 전담 컨트롤러
 * 웹에 대한 요청을 직접 받지 않으므로 서블릿일 필요없다.
 */

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.academy.model2app.controller.Controller;
import com.academy.model2app.movie.model.MovieManager;

public class MovieController implements Controller {

	MovieManager manager= new MovieManager();
	//컨트롤러의 5대 업무 중 3단계를 수행
	public void execute(HttpServletRequest request,HttpServletResponse response) {
		String movie = request.getParameter("movie");
		String msg = manager.getAdvice(movie);
		
		//4단계 뷰페이지로 가져갈 것이 있을 경우
		request.setAttribute("data", msg);
		
		
	}
	//DispatchServlet이 어떤 뷰 페이지를 보여줘야할지 결정
	public String getViewPage() {
		return"/movie/result";
	}
}
