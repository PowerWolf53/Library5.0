package by.etc.library.controller;

import javax.servlet.http.HttpServletRequest;

import by.etc.library.service.ServiceException;
import by.etc.library.service.ServiceFactory;
import by.etc.library.service.UserService;

public class CancelOrderCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		int orderId=Integer.parseInt(request.getParameter(RequestParam.ORDER_ID));
		UserService serv =ServiceFactory.getInstance().getUserService();
		try {
			serv.cancelOrder(orderId);
		} catch (ServiceException e) {
			
			request.setAttribute(RequestParam.ERROR, e.getMessage());
		}
		return null;
	}

}
