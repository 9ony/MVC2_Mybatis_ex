package common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SampleAction extends AbstractAction {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)
	throws Exception{
		System.out.println("SampleAction의 excute()호출됨...");
		
		req.setAttribute("msg", "From SampleAction: 안녕 샘플??");
		this.setViewPage("/template.jsp");
		this.setRedirect(false);
	}
}