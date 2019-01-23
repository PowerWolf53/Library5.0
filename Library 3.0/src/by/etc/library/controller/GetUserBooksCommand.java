package by.etc.library.controller;

import javax.servlet.http.HttpServletRequest;

import by.etc.library.bean.ServiceResponse;
import by.etc.library.service.ServiceException;
import by.etc.library.service.ServiceFactory;
import by.etc.library.service.UserService;

public class GetUserBooksCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		UserService serv= ServiceFactory.getInstance().getUserService();
		try {
			ServiceResponse resp=serv.getUserBooks();
			request.setAttribute(RequestParam.USER_BOOKS, resp.getRequestParam(RequestParam.USER_BOOKS));
		} catch (ServiceException e) {
	
			request.setAttribute(RequestParam.ERROR, e.getMessage());
			return JSPPage.PANEL_PAGE;
		}
		return JSPPage.USER_BOOKS_PAGE;
	}

}
