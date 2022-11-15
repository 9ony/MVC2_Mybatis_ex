package board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAOMyBatis;
import board.model.BoardVO;
import common.controller.AbstractAction;

public class BoardViewAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		BoardDAOMyBatis dao=new BoardDAOMyBatis();
		String numstr = req.getParameter("num");
		if(numstr==null||numstr.trim().isEmpty()) {
			this.setViewPage("boardList.do");
			this.setRedirect(true);
			return;
		}
		int num = Integer.parseInt(numstr.trim());
		BoardVO boardview=dao.viewBoard(num);
		
		req.setAttribute("board", boardview);

		this.setViewPage("board/boardView.jsp"); //경로
		this.setRedirect(false); //포워드방식
	}

}
