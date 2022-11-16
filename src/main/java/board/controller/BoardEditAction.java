package board.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.model.BoardDAOMyBatis;
import board.model.BoardVO;
import common.controller.AbstractAction;

public class BoardEditAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//post일때 한글처리
		ServletContext application=req.getServletContext();
		String upDir=application.getRealPath("/Upload");
		System.out.println("upDir="+upDir);
		
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
		String userid = mr.getParameter("userid");
		String filename = mr.getFilesystemName("filename");
		
		File file=mr.getFile("filename");
		long filesize = 0;
		if(filename!=null) {
			filesize = file.length();
			//예전에 첨부한 파일명 얻기
			String old_file=mr.getParameter("old_file");
			if(old_file!=null&& !old_file.trim().isEmpty()) {
				File delFile=new File(upDir, old_file);
				if(delFile!=null) {
					boolean b = delFile.delete();
					String str = b?"기존파일 삭제성공":"기존파일 삭제실패";
					System.out.println(str);
				}
			}
		}
		System.out.println("test filename"+req.getParameter("filename"));
		System.out.println("test fileesize"+req.getParameter("filesize"));
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
