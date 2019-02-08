package by.etc.library.controller;

import javax.servlet.http.HttpServletRequest;

import by.etc.library.service.BookService;
import by.etc.library.service.ServiceException;
import by.etc.library.service.ServiceFactory;

public class BookIncrementCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		BookService serv=ServiceFactory.getInstance().getBookService();
		String name=request.getParameter(RequestParam.NAME);
		String ammount = request.getParameter(RequestParam.AMMOUNT);
		try {
			serv.incrementAmmount(name, ammount);
		} catch (ServiceException e) {
			request.setAttribute(RequestParam.ERROR,e.getMessage());
			return JSPPage.PANEL_PAGE;
		}
		return JSPPage.PANEL_PAGE;
	}

}
