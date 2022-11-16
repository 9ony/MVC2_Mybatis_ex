package board.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.controller.AbstractAction;

public class UpLoadEndAction2 extends AbstractAction {
	String upDir="C:/test"; //업로드위치 임의로 설정
	File Folder = new File(upDir);

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//디렉토리가 없을경우 디렉토리를 생성합니다.
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
		//MultipartRequest 매개변수 (request객체, 경로 , 파일크기, 인코딩타입, 파일명중복처리메소드);
		//new DefaultFileRenamePolicy() 안넣으면 기본값은 덮어쓴다.
		MultipartRequest mr=new MultipartRequest(req , upDir ,10*1024*1024,"UTF-8",new DefaultFileRenamePolicy());
		System.out.println("파일 업로드 성공"+upDir+"에서 확인하세요");
		
		//MultipartRequest 가 req에 있는 정보를 추출하기 때문에 req로 파라미터를 가져올수없다
		//그래서 MultipartRequest에 getParameter메소드로 가져와야한다

		//사용자명
		//String name = req.getParameter("name"); [x]
		String name = mr.getParameter("name"); //[o]
		System.out.println("name :"+name);
		
		//파일명
		//파일명은 getFilesystemName을 이용해야된다
		//String fname=mr.getParameter("fname");[x]
		String fname=mr.getFilesystemName("fname"); //[o]
		System.out.println("fname :"+fname);
		
		//첨부파일크기
		File file=mr.getFile("fname");
		long fsize=0;
		if(file!=null) {
			fsize=file.length();
		}
		req.setAttribute("content", "파일 업로드 성공"+upDir+"에서 확인하세요");
		req.setAttribute("name", name);
		req.setAttribute("fname", fname);
		req.setAttribute("fsize", fsize);
		this.setViewPage("board/uploadResult.jsp");
		this.setRedirect(false);
		//파일을 실질적으로 업로드해주는 action cos라이브러리 사용했음
	}
	/*
	 * public List[] pathset(String path) { List<String> pathqnsgkf = new
	 * List<String>();
	 * 
	 * return pathqnsgkf; }
	 */

}
