package by.etc.library.controller;

import javax.servlet.http.HttpServletRequest;

import by.etc.library.bean.ServiceResponse;
import by.etc.library.service.ServiceException;
import by.etc.library.service.ServiceFactory;
import by.etc.library.service.UserService;

public class AuthorisationCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		String login=request.getParameter(RequestParam.LOGIN);
		String password=request.getParameter(RequestParam.PASSWORD);
		UserService userService=ServiceFactory.getInstance().getUserService();
		try {			
			ServiceResponse resp = userService.signIn(login, password);
			String id=(String) resp.getSessionParam(SessionParam.ID);
			String status=(String) resp.getSessionParam(SessionParam.STATUS);
			request.getSession().setAttribute(SessionParam.ID, id);
			request.getSession().setAttribute(SessionParam.STATUS, status);
			return JSPPage.MAIN_PAGE;
		} catch (ServiceException e) {
			request.getSession().invalidate();
			String error=e.getMessage();
			request.setAttribute(RequestParam.ERROR, error);
			return JSPPage.SGNIN_PAGE;
		}
	
	}

}
