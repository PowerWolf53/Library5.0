package by.etc.library.controller;

import javax.servlet.http.HttpServletRequest;

import by.etc.library.service.ServiceException;
import by.etc.library.service.ServiceFactory;
import by.etc.library.service.UserService;

public class SubmitOrderCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		int userId=Integer.parseInt(request.getParameter(RequestParam.USER_ID));
		int bookId=Integer.parseInt(request.getParameter(RequestParam.BOOK_ID));
		int orderId=Integer.parseInt(request.getParameter(RequestParam.ORDER_ID));
		String date=request.getParameter(RequestParam.EXPIRE_DATE);
		UserService serv = ServiceFactory.getInstance().getUserService();
		try {
			serv.SubmitOrder(orderId, userId, bookId, date);
		} catch (ServiceException e) {
			
			request.setAttribute(RequestParam.ERROR, e.getMessage());
		}
		return null;
	}

}
