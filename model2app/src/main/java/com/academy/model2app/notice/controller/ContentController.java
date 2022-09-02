package com.academy.model2app.notice.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.academy.model2app.controller.Controller;
import com.academy.model2app.domain.Notice;
import com.academy.model2app.model.repository.NoticeDAO;

//3단계-일시키기 4단계-결과 저장 
public class ContentController implements Controller{
	NoticeDAO noticeDAO= new NoticeDAO();
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int notice_id=Integer.parseInt(request.getParameter("notice_id"));
		Notice notice = noticeDAO.select(notice_id);//3단계 
		
		//4단계
		request.setAttribute("notice", notice);
	}
	@Override
	public String getViewName() {
		
		return "/notice/result/content";
	}
	@Override
	public boolean isForward() {
		//notice_id를 가져가야하므로 foward해야함
		return true;
	}
}
