package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.BoardDAOMyBatis;
import board.model.BoardVO;
import common.controller.AbstractAction;
import user.model.UserVO;

public class BoardEditFormAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//로그인체크
		HttpSession session=req.getSession();
		UserVO user=(UserVO)session.getAttribute("loginUser");
//		if(user==null) {
//			req.setAttribute("msg","로그인해야 가능해요");
//			req.setAttribute("loc","javascript:history.back()");
//			
//			this.setViewPage("message.jsp");
//			this.setRedirect(false);
//			return;
//		}
		// 1. 수정할 글번호 받기
		BoardDAOMyBatis dao=new BoardDAOMyBatis();
		String numstr = req.getParameter("num");
//		numstr = " "; //test
		// 2. 유효성 체크
		if(numstr==null||numstr.trim().isEmpty()) {
			this.setViewPage("../boardList.do");
			this.setRedirect(true);
			return;
		}
		// 3. DAO통해서 글번호 가져오기
		int num = Integer.parseInt(numstr.trim());
		BoardVO boardview=dao.viewBoard(num);
		if(!boardview.getUserid().equals(user.getUserid())) {
			req.setAttribute("msg","글쓴이만 수정이 가능해요");
			req.setAttribute("loc","javascript:history.back()");
			
			this.setViewPage("/message.jsp");
			this.setRedirect(false);
			return;
		}
		// 4. request(req)에 저장 key값 board
		req.setAttribute("board", boardview);
		
		// 5. view페이지는 boardEdit.jsp
		this.setViewPage("/board/boardEdit.jsp");
		this.setRedirect(false);
	}

}
