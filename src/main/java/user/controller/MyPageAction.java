package user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;
// "/user/myPage.do ====>  /member/myPage.jsp
public class MyPageAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		this.setViewPage("../member/myPage.jsp");
		this.setRedirect(false);
	}

}
