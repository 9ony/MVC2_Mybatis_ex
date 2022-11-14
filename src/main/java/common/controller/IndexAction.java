package common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexAction extends AbstractAction {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)
	throws Exception{
		System.out.println("IndexAction의 excute()호출됨...");
		
		req.setAttribute("msg", "안녕 인덱스??");
		
		//뷰페이지 지정
		this.setViewPage("/index.jsp");
		//이동방식 지정
		this.setRedirect(false);// false일때 forward방식 이동 , ture 일땐 redirect
	}
}
