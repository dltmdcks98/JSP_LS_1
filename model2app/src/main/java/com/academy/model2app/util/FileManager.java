package com.academy.model2app.util;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.academy.model2app.domain.Gallery;

import lombok.Data;

/*
 * 파일과 관련된 업무처리를 담당하는 유틸리티 클래스
 */
@Data
public class FileManager {
	DiskFileItemFactory factory = new DiskFileItemFactory();
	int maxSize = 5 * 1024 * 1024;
	FileItem fileItem;//파일 정보가 들어있는 아이템
	//Controller에 공유하기 위함 
	private String dest;
	private String extension;

	// 파일을 지정한 경로에 저장하는 메서드
	public Gallery getParam(HttpServletRequest request) {
		//jsp의 application 내장객체이자 ServletContext 자료형인 객체를 request객체로부터 얻어오는 방법
		String savePath= request.getServletContext().getRealPath("/data");//저장 디렉토리 경로
		dest=savePath;
		
		// 업로드 전 설정정보를 관리하는 객체
		factory.setSizeThreshold(maxSize);
		factory.setRepository(new File(savePath));
		
		// 업로드를 담당하는 객체
		ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
		Gallery gallery = new Gallery();
		String ext = null;// 확장자
		try {
			List<FileItem> uploadList = servletFileUpload.parseRequest(request);// 요청객체를 분석 후, 텍스트와 파일을 FileItem에 담는다
			// 지금 상황에서는 input="text"3개 input type ="file"1개 = 4개를 list에 담는다.
			System.out.println("발견된 item의 수 :" + uploadList.size());

			for (int i = 0; i < uploadList.size(); i++) {
				FileItem item = uploadList.get(i);
				if (item.isFormField()) {// text 파라미터를 받은 경우
					System.out.println(item.getFieldName() + "의 값은" + item.getString());

					switch (item.getFieldName()) {
					case "title":
						gallery.setTitle(item.getString());
						break;
					case "writer":
						gallery.setWriter(item.getString());
						break;
					case "content":
						gallery.setContent(item.getString());
						break;
					}
					
				} else {// 바이너리 파일인 경우
					System.out.println("파일명은" + item.getName());
					ext = FileManager.getExt(item.getName());
					extension=ext;
					fileItem=item;//멤버변수에 보관해야, 다른 메서드에서 접근할 수 있음
				}
			}
			//insert 
			
			
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gallery;
	}

	
	//파일을 지정한 경로에 저장
	public void saveAs(String path) {
		System.out.println(path);
		try {
			fileItem.write(new File(path));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// 파일명 추출

	// 확장자 추출
	public static String getExt(String path) {
		int index = path.lastIndexOf(".");// 문자열 내의 가장 마지막 .의 인덱스 반환
		return path.substring(index + 1, path.length());
	}

//	public static void main(String[] args) {
//		String result=getExt("d:/asdf/asdfasd/fasdfasdf/xx.xx.xx.jpg");
//		System.out.println(result);
//	}
}
