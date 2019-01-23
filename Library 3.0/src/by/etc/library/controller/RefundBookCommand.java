package by.etc.library.controller;

import javax.servlet.http.HttpServletRequest;

import by.etc.library.service.ServiceException;
import by.etc.library.service.ServiceFactory;
import by.etc.library.service.UserService;

public class RefundBookCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		int id=Integer.parseInt(request.getParameter(RequestParam.PRIVATE_BOOK_ID));
		UserService serv=ServiceFactory.getInstance().getUserService();
		try {
			serv.refundBook(id);
		} catch (ServiceException e) {
			request.setAttribute(RequestParam.ERROR, e.getMessage());
			return JSPPage.PANEL_PAGE;
		}
		return JSPPage.PANEL_PAGE;
	}

}
