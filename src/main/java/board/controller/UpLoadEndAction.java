package board.controller;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;

public class UpLoadEndAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//컨텐트타입, 파일크기
		String ctype=req.getContentType();
		long len=req.getContentLengthLong();
		//int len2=req.getContentLength(); int로도 받을순 있다
		
		//첨부파일=> request body에 포함되어 오는 파일 데이터를 스트림 연결해 읽은후
		// 브라우저 출력해보자.
		ServletInputStream in=req.getInputStream();
		byte[] data=new byte[1024]; //스트림을 담을 배열
		int n=0, count=0 , total=0;
		String content="<xmp>";
		while((n=in.read(data))!=-1) {
			String str=new String(data,0,n);
			content+=str;
			count++;
			total+=n;
		}
		content+="</xmp>";//첨부파일데이터를 출력하려면 xmp로 감싸줘야함
		
		req.setAttribute("ctype", ctype);
		req.setAttribute("len", len);
		req.setAttribute("content", content);
		req.setAttribute("count", total);
		
		in.close();
		
		this.setViewPage("board/uploadResult.jsp");
		this.setRedirect(false);
		//업로드 파일의 정보를 출력해주는 action이다
	}

}
