package user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;
import user.model.UserDAOMyBatis;
import user.model.UserVO;

public class MemberAddAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		req.setCharacterEncoding("UTF-8");
		//1.이름,아이디,비밀번호,연락처,우편번호,주소1,주소2
		String name = req.getParameter("name");
		String userid = req.getParameter("userid");
		String pwd = req.getParameter("pwd");
		String hp1 = req.getParameter("hp1");
		String hp2 = req.getParameter("hp2");
		String hp3 = req.getParameter("hp3");
		String post = req.getParameter("post");
		String addr1 = req.getParameter("addr1");
		String addr2 = req.getParameter("addr2");
		//2.유효성체크 =>joinForm.do
		if(name==null||userid==null||pwd==null||hp1==null||hp2==null||hp3==null){
			this.setViewPage("joinForm.do");
			this.setRedirect(true);
			return;
		}
		//3.UserVO에 담아주기
		UserVO user=new UserVO(0,name,userid,pwd,hp1,hp2,hp3,post,addr1,addr2,null,1000,0,"");
	
		//4.UserDAOMyBatis의 insertUser(vo)
		UserDAOMyBatis dao=new UserDAOMyBatis();
		int n = dao.insertUser(user);
		
		//5.회원가입 성공실패
		String str=(n>0)?"회원가입 처리 완료":"가입 실패";
		String loc=(n>0)?"login.do":"javascript:histroy.back()";
		req.setAttribute("msg", str);
		req.setAttribute("loc", loc);
		this.setViewPage("message.jsp");
		this.setRedirect(false);
	}

}
