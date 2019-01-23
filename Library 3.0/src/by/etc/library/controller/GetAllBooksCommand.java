package by.etc.library.controller;

import javax.servlet.http.HttpServletRequest;

import by.etc.library.bean.ServiceResponse;
import by.etc.library.service.BookService;
import by.etc.library.service.ServiceException;
import by.etc.library.service.ServiceFactory;

public class GetAllBooksCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		BookService serv= ServiceFactory.getInstance().getBookService();
		ServiceResponse resp=null;
		try {
			resp=serv.getAllBooks();
			request.setAttribute(RequestParam.BOOKS, resp.getRequestParam("all_books"));
		} catch (ServiceException e) {
			request.setAttribute(RequestParam.ERROR, e.getMessage());
			return JSPPage.MAIN_PAGE;
		}
		return JSPPage.BOOK_CATALOG_PAGE;
	}

}
