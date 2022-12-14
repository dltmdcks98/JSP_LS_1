package com.academy.model2app.notice.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.academy.model2app.controller.Controller;
import com.academy.model2app.domain.Notice;
import com.academy.model2app.model.repository.NoticeDAO;

/*
 * Controller의 임무 중 3단계(알맞은 모델 객체에 일시키기)
 * 					  4단계 저장할 것이 있다면 저장(DML의 경우 생략)	 
 */
public class RegistController implements Controller{
	NoticeDAO noticeDAO= new NoticeDAO();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		Notice notice = new Notice();
		notice.setTitle(title);
		notice.setWriter(writer);
		notice.setContent(content);
			
		int result = noticeDAO.insert(notice);//일시키기
		
	}
	
	@Override
	public String getViewName() {
		return "/notice/result/write";
	}
	
	//JSP로 가져갈 것이 없으므로 데이터를 유지할 필요가 없음 
	//따라서 request는 죽어도 되므로 응답을 하고, 응답을 하게되면 
	//서버와 클라이언트는 연결이 끊기고 서버의 request, response thread는 소멸함
	public boolean isForward() {
		return false;
	}
}
