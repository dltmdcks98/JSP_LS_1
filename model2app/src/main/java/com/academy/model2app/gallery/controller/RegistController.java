package com.academy.model2app.gallery.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.academy.model2app.controller.Controller;
import com.academy.model2app.domain.Gallery;
import com.academy.model2app.util.FileManager;
/*
 * 텍스트 파라미터 뿐만 아니라 바이너리 파일이 포함된 업로드 요청을 처리
 * 지난 번에는 이용한 cos.jar(Oreilly사 제공)
 * 지금은 apache.org에서 제작한 apache.commons프로젝트의 하위 컴포넌트 fileupload컴포넌트
 */
public class RegistController implements Controller{
	DiskFileItemFactory factory = new DiskFileItemFactory();
	int maxSize = 5*1024*1024;
	String savePath; 
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		//jsp의 application 내장객체이자 ServletContext 자료형인 객체를 request객체로부터 얻어오는 방법
		savePath= request.getServletContext().getRealPath("/data");
		
		//업로드 전 설정정보를 관리하는 객체
		factory.setSizeThreshold(maxSize);
		factory.setRepository(new File(savePath));

		//설정 정보
		
		
		//업로드를 담당하는 객체
		ServletFileUpload servletFileUpload=new ServletFileUpload(factory);
		Gallery gallery = new Gallery();
		String ext=null;//확장자
		try {
			List<FileItem> uploadList=servletFileUpload.parseRequest(request);//요청객체를 분석 후, 텍스트와 파일을 FileItem에 담는다
			//지금 상황에서는 input="text"3개 input type ="file"1개 = 4개를 list에 담는다.
			System.out.println("발견된 item의 수 :"+uploadList.size());
			
				for(int i=0;i<uploadList.size();i++) {
					FileItem item = uploadList.get(i);
					if(item.isFormField()) {//text 파라미터를 받은 경우
						System.out.println(item.getFieldName()+"의 값은"+item.getString());
						
						switch (item.getFieldName()) {
						case "title": gallery.setTitle(item.getString());break;
						case "writer": gallery.setWriter(item.getString());break;
						case "content": gallery.setContent(item.getString());break;
						}
						gallery.setTitle(savePath);
					}else {//바이너리 파일인 경우
						System.out.println("파일명은"+item.getName());
						ext = FileManager.getExt(item.getName());					}
			}
			
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public String getViewName() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isForward() {
		// TODO Auto-generated method stub
		return false;
	}
}
