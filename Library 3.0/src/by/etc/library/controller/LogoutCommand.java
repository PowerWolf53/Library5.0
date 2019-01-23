package by.etc.library.controller;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		request.getSession().invalidate();
		return JSPPage.SGNIN_PAGE;
	}

}
