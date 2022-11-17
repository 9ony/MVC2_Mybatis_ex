package user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;

public class LoginAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
			//경로
			this.setViewPage("login/login.jsp");
			//이동방식 지정
			this.setRedirect(false);

	}

}
