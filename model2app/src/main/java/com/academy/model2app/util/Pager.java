package com.academy.model2app.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import lombok.Data;

@Data
public class Pager {
	private int totalRecord;
	private int pageSize=10;
	private int totalPage;
	private int blockSize=10;
	private int currentPage=1;
	private int firstPage;
	private int lastPage;
	private int curPos;//페이지당 시작 index(List의 )
	private int num;//페이지당 시작 번호 
	
	public void init(List list, HttpServletRequest request) {
		totalRecord=list.size();
		totalPage = (int)Math.ceil((float)totalRecord/pageSize);
		if(request.getParameter("currentPage")!=null) {//파라미터가 넘어온다면 (페이지 링크 번호를 눌렀다면
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		firstPage = currentPage-(currentPage-1)%blockSize;
		lastPage = firstPage+(blockSize-1);
		curPos = (currentPage-1)*pageSize;
		num= totalRecord-curPos;
	}
}
