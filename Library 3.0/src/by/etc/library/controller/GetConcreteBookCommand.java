package by.etc.library.controller;

import javax.servlet.http.HttpServletRequest;

import by.etc.library.bean.ServiceResponse;
import by.etc.library.service.BookService;
import by.etc.library.service.ServiceException;
import by.etc.library.service.ServiceFactory;

public class GetConcreteBookCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		int id =Integer.parseInt(request.getParameter(RequestParam.ID));
		BookService serv = ServiceFactory.getInstance().getBookService();
		try {
			ServiceResponse resp = serv.getConcreteBook(id);
			request.setAttribute(RequestParam.BOOK, resp.getRequestParam(RequestParam.BOOK));
		} catch (ServiceException e) {
			request.setAttribute(RequestParam.ERROR, e.getMessage());
		
		}
		return JSPPage.BOOK_INFO_PAGE;
	}

}
