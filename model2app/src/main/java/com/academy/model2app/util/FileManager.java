package com.academy.model2app.util;

/*
 * 파일과 관련된 업무처리를 담당하는 유틸리티 클래스
 */
public class FileManager {

	
	//확장자 추출 
	public static String getExt(String path) {
		int index = path.lastIndexOf(".");//문자열 내의 가장 마지막 .의 인덱스 반환
		return path.substring(index+1, path.length());
	}
	
//	public static void main(String[] args) {
//		String result=getExt("d:/asdf/asdfasd/fasdfasdf/xx.xx.xx.jpg");
//		System.out.println(result);
//	}
}
