package board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.BoardDAOMyBatis;
import board.model.BoardVO;
import common.controller.AbstractAction;
import user.model.UserVO;
//BoardListAction.java
public class BoardListAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//0. 현재 페이지
		String cpStr=req.getParameter("cpage");
		if(cpStr==null||cpStr.trim().isEmpty()) {
			cpStr="1";//1페이지를 기본값으로 설정
		}
		int cpage=Integer.parseInt(cpStr.trim());
		if(cpage<1) {
			cpage=1;
		}
		String findType=req.getParameter("findType");
//		System.out.println(findType);//체크용
		String findKeyword=req.getParameter("findKeyword");
//		System.out.println(findKeyword);//체크용
		
		//null이오면 빈문자열 반환
		if(findType==null) {
			findType="";
		}
		if(findKeyword==null) {
			findKeyword="";
		}
		
		//쿼리문 서블릿에서 미리작성
		String qStr="&findType="+findType+"&findKeyword="+findKeyword;
		
		BoardDAOMyBatis dao=new BoardDAOMyBatis();
		//페이징 처리//
		//1. 총 게시글 수 구하기
		int totalCount=dao.getTotalCount(findType,findKeyword);
		
		//2. 한 페이지당 개수
		int pageSize=5;
		
		//3. 페이지 수 구하기
		/*totalCount	pageSize		pageCount
		1~4/ 5				5				W 1		
		6~9/ 10				5				 2
		11~14/ 15			5				3				
		
		if(totalCount%pageSize==0){
		     pageCount=totalCount/pageSize;	
		}else{
		    pageCount=totalCount/pageSize +1;	
		}
		pageCount=(totalCount-1)/pageSize +1;
		 */
		int pageCount=(totalCount-1)/pageSize +1;
		
		if(pageCount<=0) {
			pageCount=1;
		}
		if(cpage>pageCount) {
			cpage=pageCount;
		}
		int end=cpage*pageSize;
		int start = end - (pageSize-1);
		
		List<BoardVO> boardArr = dao.listBoard(start, end,findType,findKeyword);
		
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("cpage", cpage);
		//qStr로 쿼리문 작성해서 보냄
		req.setAttribute("qStr", qStr);
		//req.setAttribute("findType", findType); 
		//req.setAttribute("findKeyword", findKeyword); //qStr로 묶음
		req.setAttribute("listBoard", boardArr);
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("pageSize", pageSize);
		this.setViewPage("board/boardList.jsp"); //경로
		this.setRedirect(false); //포워드방식
	}

}
