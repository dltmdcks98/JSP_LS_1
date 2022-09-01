package com.academy.model2app.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 모든 요청마다 1:1 대응하는 컨트롤러를 전면에 내세우면 오히려 유지 보수성이 떨어진다.
 */
public class DispatcherServlet extends HttpServlet{
	FileInputStream fis;
	Properties props;

	//생성자, init()으로 fis에 메모리에 올린다. doXX에 올리지 않는 이유는 요청이 올때마다 메모리에  File을 올리는건 낭비여서 서블릿이실행될때 처음만 실행되도록 하기 위해

	@Override
	public void init(ServletConfig config) throws ServletException {
		try {
			//자바 웹어플리케이션은 플랫폼에 독립적이어야 하므로, 자원의 주소는 class내에 하드코딩해서는 안된다.
			ServletContext context=config.getServletContext();
//			String path = context.getRealPath("/WEB-INF/mapping.properties");
			
			//path 안에 있는 변수를 XML 파일에 넣고 최초 요청을 받을 때 변수 값으로 받는다.
			String param = config.getInitParameter("contextConfigLocation");
			String path = context.getRealPath(param);
			
			//String path = "D:/OneDrive-LSC/SLAcademy/JSP_LS_1/model2app/src/main/webapp/WEB-INF/mapping.properties"; 이런 하드코딩은 유지보수에 좋지 않다.
			fis= new FileInputStream(path);
			props = new Properties();
			props.load(fis);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//모든 요청을 이 서블릿이 받아야한다.
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doRequest(req, resp);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doRequest(req, resp);
	}
	//클라이언트의 요청이 어떤 방식이던 상관 없이 요청을 받기위해 공통 메소드에서 컨트롤러의 요청을 처리
	protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("요청을 받았습니다.");
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		System.out.println(uri);
		Controller controller = null;
		//2)요청 분석
		//주의) 검색 결과는 컨트롤러 클래스 자체가 아닌, 단지 그 클래스의 경로일 뿐이다.(String형)
		String controllerClassName = props.getProperty(uri);
		//String 경로를 이용하여 실제 클래스의 인스턴스를 생성하는 법 
		try {
			//()안에 있는 경로를 통해 static 영역으로 올린다. 원본만 올리는 것 아직 인스턴스가 올라오지 않음, new를 하지 않음 
			Class controllerClass=Class.forName(controllerClassName);
			controller =(Controller) controllerClass.newInstance();//new 연산자를 만드는 법, 구버젼
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		if(uri.equals("/movie.do")) {	//영화에 대한 요청인지
			//영화 전담자인 MovieController 에게 업무를 맡긴다.
			controller = new BloodController();
			
		}else if(uri.equals("/blood.do")) {	//혈액형에 대한 요청인지
			
		}
		*/
		
		controller.execute(request, response);
		//5)알맞은 뷰 페이지
		
		//포워딩 하기 전에 맵핑파일에서 검색을 해야함 실제 jsp파일 경로를 얻기 위해
		String viewName = controller.getViewName();
		String viewPage = props.getProperty(viewName);
		RequestDispatcher dis = request.getRequestDispatcher(viewPage);
		dis.forward(request, response);

	}
	
	//서블릿이 일을 다하고 소면될때, 호출되는 생명주기 메서드
	public void destroy() {
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
