package by.etc.library.controller;

import javax.servlet.http.HttpServletRequest;

import by.etc.library.service.ServiceException;
import by.etc.library.service.ServiceFactory;
import by.etc.library.service.UserService;

public class OrderBookCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		String userId=request.getParameter("id");
		String bookId=request.getParameter("book_id");
		UserService serv= ServiceFactory.getInstance().getUserService();
		try {
			serv.orderBook(Integer.parseInt(bookId), Integer.parseInt(userId));
		} catch (NumberFormatException e) {
			request.setAttribute(RequestParam.ERROR, "something went wrong");
		} catch (ServiceException e) {
			request.setAttribute(RequestParam.ERROR, e.getMessage());
		}
		return null;
	}

}
