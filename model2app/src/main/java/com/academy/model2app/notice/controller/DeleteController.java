package com.academy.model2app.notice.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.academy.model2app.controller.Controller;
import com.academy.model2app.model.repository.NoticeDAO;

public class DeleteController implements Controller{
	NoticeDAO noticeDAO = new NoticeDAO();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int notice_id=Integer.parseInt(request.getParameter("notice_id"));
		int result =noticeDAO.delete(notice_id);
		
	}
	@Override
	public String getViewName() {
		// TODO Auto-generated method stub
		return "/notice/result/delete";
	}
	@Override
	public boolean isForward() {
		
		return false;
	}
	
}
