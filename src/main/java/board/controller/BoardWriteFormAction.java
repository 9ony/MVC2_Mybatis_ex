package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;
//boardWrite.do
public class BoardWriteFormAction extends AbstractAction {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)
	throws Exception{
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
