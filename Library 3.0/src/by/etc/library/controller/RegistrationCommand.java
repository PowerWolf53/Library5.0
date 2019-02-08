package by.etc.library.controller;

import javax.servlet.http.HttpServletRequest;


import by.etc.library.service.ServiceException;
import by.etc.library.service.ServiceFactory;
import by.etc.library.service.UserService;

public class RegistrationCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		String login=request.getParameter(RequestParam.LOGIN);
		String password=request.getParameter(RequestParam.PASSWORD);
		String name=request.getParameter(RequestParam.NAME);
		String surname=request.getParameter(RequestParam.SURNAME);
		System.out.println(name+surname);
		UserService userService=ServiceFactory.getInstance().getUserService();
		try {
			 userService.registration(login, password,name,surname);
			return JSPPage.SGNIN_PAGE;
		} catch (ServiceException e) {
			request.getSession().invalidate();
			String error=e.getMessage();
			request.setAttribute(RequestParam.ERROR, error);
			return JSPPage.SGNIN_PAGE;
		}
		
	}
	
	
}
