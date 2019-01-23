package by.etc.library.controller;

import javax.servlet.http.HttpServletRequest;

import by.etc.library.bean.ServiceResponse;
import by.etc.library.service.BookService;
import by.etc.library.service.ServiceException;
import by.etc.library.service.ServiceFactory;


public class GetOrdersCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		BookService serv = ServiceFactory.getInstance().getBookService();
		try {
			ServiceResponse resp = serv.getOrders();
			request.setAttribute(RequestParam.ORDERS, resp.getRequestParam(RequestParam.ORDERS));
		} catch (ServiceException e) {
			request.setAttribute(RequestParam.ERROR, e.getMessage());
			return JSPPage.ORDERS_PANEL_PAGE;
		}
		return JSPPage.ORDERS_PANEL_PAGE;
	}

}
