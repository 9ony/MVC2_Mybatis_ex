package common.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*FrontController : *.do 패턴의 모든 요청을 받아들인다.
 * - Command.properties 파일에 있는 매핑 정보를 읽어들여 해당 요청uri와 매핑되어 있는
 *   SubController(XXXAction)을 찾아 객체화 한 뒤 해당 객체의 메소드(execute)를 호출한다.
 * - 서브 컨트롤러는 해당 작업을 수행한 뒤에 다시 FrontController로 돌아와 보여줘야 할 View
 *   페이지(JSP) 정보를 넘긴다.
 * - FrontController는 해당 뷰페이지로 이동시킨다. (forward방식 이동 or redirect방식 이동)    
 * */
// ***Command.properties파일의 절대경로를 value에 넣어줘야함!!***
@WebServlet(
		urlPatterns = { "*.do" },
		initParams = { 
				@WebInitParam(name = "config", 
						value = "C:\\myjava\\workspace\\MvcWeb\\src\\main\\webapp\\WEB-INF\\Command.properties")
		})
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private HashMap<String, Object> cmdMap=new HashMap<>();
	
	@Override
	public void init(ServletConfig conf) throws ServletException{
		System.out.println("init()이 호출됨..");
		String props = conf.getInitParameter("config");
		//web.xml에 초기파라미터(init-param)에 저장되어 있는 config의 값을 받아서 props에 할당해줌.
		//System.out.println("props =="+props);
		//props 체크
		Properties pr=new Properties();
		//Properties 객체생성
		/*
		 * *Key=Value*형식으로 파라미터 정보들을 저장하기 위한 파일 확장자를 의미
		 * 주로 응용 프로그램에 대한 환경설정정보,
		 * DB와 연결하기 위한 DB환경설정정보 등을 저장할 때
		 * properties파일을 만들어 그 곳에 저장
		 */
		try {
			FileInputStream fis = new FileInputStream(props);
			pr.load(fis);
			//Command.Properties 파일 정보를 FileInputStream fis로 읽어들여서 Properties(pr)로 key,value형태로 저장
			System.out.println(fis);
			if(fis!=null) fis.close();
//			String val=pr.getProperty("/index.do"); //키값이 /index.do인 value갑 저장
//			System.out.println("val= "+val);        // 출력 (TEST)
			Set<Object> set = pr.keySet();
			// pr에 저장된 키값을 set에 저장 *set은 중복된값은 저장하지않음
			if(set==null) return;
			for(Object key:set) {
				//https://codingwell.tistory.com/147
				//ClassName을 메모리에 올려주기위해작업하는중
				String cmd=key.toString();//key값 스트링변환 > cmd저장 "/index.do"
				String className=pr.getProperty(cmd);// value값 저장 "common.controller.IndexAction"
				if(className!=null) {
					className=className.trim();//메모리에올릴때 앞뒤 공백제거
				}
				System.out.println(cmd+": "+className);
				//1.class명이 className인 객체를 cls에 클래스객체로 저장
				Class<?> cls=Class.forName(className);
				System.out.println("cls : "+cls);
				//2.cls의 생성자를 얻어옴
				//Constructor<?> cmdCon = cls.getDeclaredConstructor();
				//3.cls를 인스턴스화함!
				//Object cmdInstance = cmdCon.newInstance();
				//2~3을 합칠수 있음.
				Object cmdInstance = cls.getDeclaredConstructor().newInstance();
//				System.out.println("==??=== : "+cls.getDeclaredConstructor().newInstance().getClass());
//				System.out.println("cmdInstance : "+cmdInstance);
				cmdMap.put(cmd, cmdInstance);
			}//for-------------
			System.out.println("cmdMap.size(): "+cmdMap.size());
		}catch(Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("GET ");
		process(request,response);
	}

	private void process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		/*
		//1.클라이언트의 요청 URI를 분석해보자.
		String uri=req.getRequestURI();
		System.out.print("uri : "+uri); // 출력 => "/MvcWeb/index.do"
		String myctx=req.getContextPath(); //"/MvcWeb"
		int len=myctx.length();
		String cmd=uri.substring(len); // "/MvcWeb/index.do"에 /MvcWeb까지 뛰어넘고 index부터 출력
		*/
		//위작업이 String cmd=req.getServletPath();와 동일하다
		String cmd=req.getServletPath(); //요청받은 uri를 cmd에 저장
		System.out.println("cmd ==> "+cmd);
		
		Object instance = cmdMap.get(cmd); //cmdMap에서 key값이 cmd인 객체를 instance에 저장하고
		if(instance==null) {
			System.out.println("Action이 null");
			throw new ServletException("Action이 null입니다.");
		}
		System.out.println("instance==?"+instance);
		
		AbstractAction action=(AbstractAction)instance;
		System.out.println("action==?"+action);
		try {
			action.execute(req, res);
			//execute()는 컨트롤러 로직을 수행 한 뒤 뷰페이지랑 이동방식을 지정한다.
			String viewPage=action.getViewPage();
			boolean isRedirect=action.isRedirect();
			System.out.println("viewPage: "+ viewPage);
			if(viewPage==null) {
				viewPage="index.jsp";
			}
			if(viewPage.equals(cmd.trim().substring(1))) {
				Exception e = new Exception("무한루프가 돌아요"+action+".java 를 확인해보세요");
				System.out.println("무한루프가 돌아요"+action+".java 를 확인해보세요");
				viewPage="index.jsp";
				throw e;
			}
			
			if(isRedirect) {
				//redirect방식으로 페이지 이동
				res.sendRedirect(viewPage);
			}else {
				//forward방식으로 이동
				RequestDispatcher disp = req.getRequestDispatcher(viewPage);
				disp.forward(req, res);
			}
		
		}catch(Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
		
		
	}//---------------
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("POST ");
		
		process(request, response);
	}

}
