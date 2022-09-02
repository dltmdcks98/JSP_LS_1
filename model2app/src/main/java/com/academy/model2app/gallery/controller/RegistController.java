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
import com.academy.model2app.model.repository.GalleryDAO;
import com.academy.model2app.util.FileManager;
/*
 * 텍스트 파라미터 뿐만 아니라 바이너리 파일이 포함된 업로드 요청을 처리
 * 지난 번에는 이용한 cos.jar(Oreilly사 제공)
 * 지금은 apache.org에서 제작한 apache.commons프로젝트의 하위 컴포넌트 fileupload컴포넌트
 */
public class RegistController implements Controller{
	FileManager fileManager=new FileManager();
	GalleryDAO galleryDAO= new GalleryDAO();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		Gallery gallery = fileManager.getParam(request);
		
		galleryDAO.insert(gallery);//db에 레코드 들어감 
		
		//mybatis에 의해 기존 Gallery DTO의 pk 값이 채워짐 <selectKey>부분
		int gallery_id = gallery.getGallery_id();
		
		//파일 업로드
		fileManager.saveAs(fileManager.getDest()+"/"+gallery_id+"."+fileManager.getExtension());//()에 전체 경로
	}
	@Override
	public String getViewName() {
		// TODO Auto-generated method stub
		return "/gallery/result/write";
	}
	@Override
	public boolean isForward() {
		return false;
	}
}
