package board.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.BoardDAOMyBatis;
import board.model.BoardVO;
import common.controller.AbstractAction;
import user.model.UserVO;

public class BoardDeleteAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//로그인체크
		HttpSession session=req.getSession();
		UserVO user=(UserVO)session.getAttribute("loginUser");
		//로그인유무 필터 처리
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
		
		String numstr = req.getParameter("num");
		if(numstr==null||numstr.trim().isEmpty()) {
			this.setViewPage(req.getContextPath()+"/boardList.do");
			this.setRedirect(true);
			return;
		}
		
		//dao 객체생성
		BoardDAOMyBatis dao=new BoardDAOMyBatis();
		
		int num = Integer.parseInt(numstr.trim());
		
		//삭제할 객체 얻어오기
		BoardVO vo=dao.viewBoard(num);
		
		if(!vo.getUserid().equals(user.getUserid())) {
			req.setAttribute("msg","글쓴이만 가능해요");
			req.setAttribute("loc","javascript:history.back()");
			
			this.setViewPage("/message.jsp");
			this.setRedirect(false);
			return;
		}
		
		if(vo.getFilename()!=null) {
			//첨부파일이 있다면 삭제
			String upDir=req.getServletContext().getRealPath("/Upload");
			File delFile=new File(upDir,vo.getFilename());
			if(delFile!=null) {
				delFile.delete();
			}
		}
		
		int n=dao.deleteBoard(num);
		req.setAttribute("deleteDel", num);
		String str=(n>0)?"글삭제 성공":"삭제실패";
		
		
		req.setAttribute("msg", str);
		req.setAttribute("loc","../boardList.do");
		//뷰페이지,경로 지정
		this.setViewPage("/message.jsp");
		//이동방식 지정 
		this.setRedirect(false);// 포워드
	}

}
