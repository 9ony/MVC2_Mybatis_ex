package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.controller.AbstractAction;
import user.model.UserVO;
//boardWrite.do
public class BoardWriteFormAction extends AbstractAction {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)
	throws Exception{
		HttpSession session=req.getSession();
		UserVO user=(UserVO)session.getAttribute("loginUser");
		if(user==null) {
			req.setAttribute("msg","로그인해야 가능해요");
			req.setAttribute("loc","login.do");
			
			this.setViewPage("message.jsp");
			this.setRedirect(false);
			return;
		}
		/*
		 * System.out.println("boardWriteFormAction의 excute()호출됨...");
		 * 
		 * req.setAttribute("msg", "보드에 글쓰기");
		 */
		
		//뷰페이지,경로 지정
		this.setViewPage("board/boardWrite.jsp");
		//이동방식 지정 
		this.setRedirect(false);// 포워드
	}
}
