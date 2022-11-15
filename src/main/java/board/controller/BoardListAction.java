package board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAOMyBatis;
import board.model.BoardVO;
import common.controller.AbstractAction;
//BoardListAction.java
public class BoardListAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		BoardDAOMyBatis dao=new BoardDAOMyBatis();
		int count=dao.getTotalCount();
		req.setAttribute("totalCount", count);
		
		List<BoardVO> boardArr=dao.listBoard();
		req.setAttribute("listBoard", boardArr);

		this.setViewPage("board/boardList.jsp"); //경로
		this.setRedirect(false); //포워드방식
	}

}
