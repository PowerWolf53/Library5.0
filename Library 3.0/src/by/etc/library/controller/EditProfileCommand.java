package by.etc.library.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import by.etc.library.service.ServiceException;
import by.etc.library.service.ServiceFactory;
import by.etc.library.service.UserService;

public class EditProfileCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		try {
			Part file = request.getPart(RequestParam.USER_FILE);
			String name=request.getParameter(RequestParam.NAME);
			String surname=request.getParameter(RequestParam.SURNAME);
			int id =Integer.parseInt((String) request.getSession().getAttribute(SessionParam.ID));
			String path=request.getServletContext().getRealPath("/");
			UserService serv=ServiceFactory.getInstance().getUserService();
			try {
				serv.editProfile(id,path, file, name, surname);
			} catch (ServiceException e) {
				request.setAttribute(RequestParam.ERROR, e.getMessage());
				return JSPPage.PROFILE_PAGE;
			}
		} catch (IOException e) {
			request.setAttribute(RequestParam.ERROR,ControllerWarning.WRONG);
			return JSPPage.PROFILE_PAGE;
		} catch (ServletException e) {
			request.setAttribute(RequestParam.ERROR,ControllerWarning.WRONG);
			return JSPPage.PROFILE_PAGE;
		}
		return JSPPage.PROFILE_PAGE;
	}

}
