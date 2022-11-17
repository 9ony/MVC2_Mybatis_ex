package user.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.controller.AbstractAction;
import user.model.NotUserException;
import user.model.UserDAOMyBatis;
import user.model.UserVO;

public class LoginEndAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String userid=req.getParameter("userid");
		String pwd=req.getParameter("pwd");
		String saveId=req.getParameter("saveId");
		if(userid==null||pwd==null||userid.trim().isEmpty()||pwd.trim().isEmpty()) {
			this.setViewPage("login.do");
			this.setRedirect(true);
			return;
		}
		UserDAOMyBatis dao = new UserDAOMyBatis();
		try {
			UserVO loginUser=dao.loginCheck(userid,pwd);
			//loginUser가 null이 아니라면 회원인증 받은 것이므로 세션에 loginUser를 저장하자
			HttpSession session=req.getSession();
			if(loginUser!=null) {
				session.setAttribute("loginUser", loginUser);
				//아이디저장 체크박스 체크시 => 쿠키에 해당아이디를 저장하자
				Cookie ck=new Cookie("uid",loginUser.getUserid());
				if(saveId!=null) {
					ck.setMaxAge(7*24*60*60); //7일
				}else {
					ck.setMaxAge(0); //쿠키삭제
				}
				ck.setPath("/"); //모든경로에서 쿠키참조가능
				res.addCookie(ck); //쿠키를 response에 포함시켜서간다.
			}//-----if
			this.setViewPage("index.do");
			this.setRedirect(true);
		
		}catch(NotUserException e) {
			req.setAttribute("msg", e.getMessage());
			req.setAttribute("loc", "javascript:history.back()");
			this.setViewPage("message.jsp");
			this.setRedirect(false);
		}
	}		
}
