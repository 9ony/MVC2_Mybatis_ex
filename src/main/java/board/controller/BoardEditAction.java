package board.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.model.BoardDAOMyBatis;
import board.model.BoardVO;
import common.controller.AbstractAction;
import user.model.UserVO;

public class BoardEditAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//로그인체크
		HttpSession session=req.getSession();
		UserVO user=(UserVO)session.getAttribute("loginUser");
		if(user==null) {
			req.setAttribute("msg","로그인해야 수정이 가능해요");
			req.setAttribute("loc","javascript:history.back()");
			
			this.setViewPage("message.jsp");
			this.setRedirect(false);
			return;
		}
		//post일때 한글처리
		ServletContext application=req.getServletContext();
		String upDir=application.getRealPath("/Upload");
		System.out.println("upDir="+upDir);
		File Folder = new File(upDir);
		if (!Folder.exists()) { //폴더체크 false면 없는것
			try{
			    Folder.mkdir(); //폴더 생성합니다.
			    System.out.println("폴더가 생성되었습니다.");
		        } 
		        catch(Exception e){
			    System.out.println("폴더생성 오류:"+e.getStackTrace());
			}        
	    }else {
			System.out.println("폴더가 이미있음");
		}
		MultipartRequest mr =null;
		try {
			mr=new MultipartRequest(req, upDir,100*1024*1024,"UTF-8", new DefaultFileRenamePolicy());
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		//req.setCharacterEncoding("UTF-8");
		//1.값받아오기
		String numStr=mr.getParameter("num");
		String subject=mr.getParameter("subject");
		String content=mr.getParameter("content");
		String userid = user.getUserid();
		String filename = mr.getFilesystemName("filename");
		
		File file=mr.getFile("filename");
		long filesize = 0;
		if(filename!=null) {
			filesize = file.length();
			//예전에 첨부한 파일명 얻기
			String old_file=mr.getParameter("old_file");
			if(old_file!=null&& !old_file.trim().isEmpty()) {
				//서버에서 예전 파일이 있다면 삭제
				File delFile=new File(upDir, old_file);
				if(delFile!=null) {
					boolean b = delFile.delete();
					String str = b?"기존파일 삭제성공":"기존파일 삭제실패";
					System.out.println(str);
				}
			}
		}
//		System.out.println("test filename"+req.getParameter("filename"));
//		System.out.println("test filesize"+req.getParameter("filesize"));
		//2.유효성 검사
		if(numStr==null||subject==null||userid==null||
				numStr.trim().isEmpty()||content.trim().isEmpty()||userid.trim().isEmpty()) {
			this.setViewPage("boardList.do");
			this.setRedirect(true);
			return;
		}
		int num=Integer.parseInt(numStr.trim());
		
		//3. vo 객체 생성
		BoardVO vo = new BoardVO(num,userid,subject,content,null,filename,filesize);
		//4.dao의 updateBoard에 
		BoardDAOMyBatis dao = new BoardDAOMyBatis();
		int n = dao.updateBoard(vo);
		
		// 5. req에 메시지 이동경로
		String str=(n>0)?"글수정 성공":"글수정 실패";
		String loc="boardList.do";
		req.setAttribute("msg", str);
		req.setAttribute("loc", loc);
		
		
		this.setViewPage("message.jsp");
		this.setRedirect(false);
		
		
	}

}
