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

public class BoardWriteAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		//1, 파일  업로드 처리==> 업로드 디렉토리의 절대경로 얻어보자. MvcWeb/src/main/webapp/Upload
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
		String subject=mr.getParameter("subject");
		String content=mr.getParameter("content");
		String userid="hong";
		String filename=mr.getFilesystemName("filename");
		
		File file=mr.getFile("filename");
		long filesize=0;
		if(file!=null) {
			filesize=file.length();
		}
		
		if(subject==null||content==null||userid==null||subject.trim().isEmpty()) {
			this.setViewPage("boardWrite.do");
			this.setRedirect(true);
			return;
		}
		BoardVO vo=new BoardVO(0,userid,subject,content,null,filename,filesize);
		
		BoardDAOMyBatis dao = new BoardDAOMyBatis();
		
		int n=dao.insertBoard(vo);
		
		String str=(n>0)?"글쓰기 성공":"실패";
		String loc=(n>0)?"boardList.do":"javascript:history.back()";
		
		
		req.setAttribute("msg", str);
		req.setAttribute("loc", loc);
		//뷰페이지,경로 지정
		this.setViewPage("message.jsp");
		//이동방식 지정 
		this.setRedirect(false);// 포워드
	}

}
