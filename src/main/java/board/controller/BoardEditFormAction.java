package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAOMyBatis;
import board.model.BoardVO;
import common.controller.AbstractAction;

public class BoardEditFormAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// 1. 수정할 글번호 받기
		BoardDAOMyBatis dao=new BoardDAOMyBatis();
		String numstr = req.getParameter("num");
		// 2. 유효성 체크
		if(numstr==null||numstr.trim().isEmpty()) {
			this.setViewPage("boardList.do");
			this.setRedirect(true);
			return;
		}
		// 3. DAO통해서 글번호 가져오기
		int num = Integer.parseInt(numstr.trim());
		BoardVO boardview=dao.viewBoard(num);
		// 4. request(req)에 저장 key값 board
		req.setAttribute("board", boardview);
		
		// 5. view페이지는 boardEdit.jsp
		this.setViewPage("board/boardEdit.jsp");
		this.setRedirect(false);
	}

}
