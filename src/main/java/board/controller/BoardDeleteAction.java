package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAOMyBatis;
import board.model.BoardVO;
import common.controller.AbstractAction;

public class BoardDeleteAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		/*
		 * BoardDeleteAction클래스 작성
		*	[1] 삭제할 글 번호 받기
		*	[2] 유효성 체크 => boardList.do  redirect이동
		*	[3] dao의 deleteBoard(num)
		*	[4] 실행결과 메시지 및 이동 경로 지정
			   => req에 저장. msg, loc
		*	[5] 뷰페이지 지정/이동방식 지정
				=> message.jsp
			--------------------------------------------------------------
		 */
		BoardDAOMyBatis dao=new BoardDAOMyBatis();
		String numstr = req.getParameter("num");
		if(numstr==null||numstr.trim().isEmpty()) {
			this.setViewPage("boardList.do");
			this.setRedirect(true);
			return;
		}
		int num = Integer.parseInt(numstr.trim());
		int n=dao.deleteBoard(num);
		req.setAttribute("deleteDel", num);
		String str=(n>0)?"글삭제 성공":"삭제실패";
		
		
		req.setAttribute("msg", str);
		req.setAttribute("loc", "boardList.do");
		//뷰페이지,경로 지정
		this.setViewPage("message.jsp");
		//이동방식 지정 
		this.setRedirect(false);// 포워드
	}

}
