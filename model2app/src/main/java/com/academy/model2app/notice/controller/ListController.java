package com.academy.model2app.notice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.academy.model2app.controller.Controller;
import com.academy.model2app.model.repository.NoticeDAO;

public class ListController implements Controller {
	
	NoticeDAO noticeDAO = new NoticeDAO();
	
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		List noticeList=noticeDAO.selectAll();
		
		//4단계 결과 페이지로 가져갈 것이 있으면 결과를 저장(DispatcherServlet에게 전달하기 위함)
		request.setAttribute("noticeList", noticeList);//저장
	}
	public String getViewName() {
		return "/notice/result/list";
	}
	
	//JSP로 가져갈 것이 있으면 포워딩을 해야함 따라서 응답을 하지 말고, 서버의 jsp자원으로 
	//포워딩을 시도하여 request를 살려둔다.
	public boolean isForward() {
		return true;
	}
}
