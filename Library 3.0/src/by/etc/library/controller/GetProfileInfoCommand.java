package by.etc.library.controller;

import javax.servlet.http.HttpServletRequest;

import by.etc.library.bean.ServiceResponse;
import by.etc.library.service.ServiceException;
import by.etc.library.service.ServiceFactory;
import by.etc.library.service.UserService;

public class GetProfileInfoCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		int id =Integer.parseInt( (String) request.getSession().getAttribute(SessionParam.ID));
		UserService serv=ServiceFactory.getInstance().getUserService();
		try {
			
			ServiceResponse resp=serv.getUserProfileInfo(id);
			request.setAttribute(RequestParam.BOOKS, resp.getRequestParam(RequestParam.BOOKS));
			request.setAttribute(RequestParam.PROFILE_INFO,resp.getRequestParam(RequestParam.PROFILE_INFO));
			request.setAttribute(RequestParam.ORDERS, resp.getRequestParam(RequestParam.ORDERS));

		} catch (ServiceException e) {
			request.setAttribute(RequestParam.ERROR, e.getMessage());
			return JSPPage.MAIN_PAGE;
		}
		return JSPPage.PROFILE_PAGE;
	}

}
