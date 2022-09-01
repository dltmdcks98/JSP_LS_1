package com.academy.model2app.listener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/*
 * 서버가 가동될때 하고 싶을때
 */
public class MyListener implements ServletContextListener{
	FileInputStream fis;
	Properties props = new Properties();
	
	//웹 컨테이너가 가동 될때 호출되는 메서드
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();//jsp 에서의 application 내장객체의 자료형
		String path = context.getInitParameter("contextConfigLocation");
		context.setAttribute("props", props);//서버가 꺼질때 까지 계속 살아남
		try {
			fis = new FileInputStream(context.getRealPath(path));
			props.load(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//웹 컨테이너가 중단될때 호출되는 메서드
	public void contextDestroyed(ServletContextEvent sce) {
		if(fis!=null) {
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
