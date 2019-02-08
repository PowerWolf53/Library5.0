package by.etc.library.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;


import by.etc.library.service.BookService;
import by.etc.library.service.ServiceException;
import by.etc.library.service.ServiceFactory;

public class BookAdditionCommand implements Command {

	private static final String ADMIN="admin";
	
	@Override
	public String execute(HttpServletRequest request) {
		if(ADMIN.equals(request.getSession().getAttribute(SessionParam.STATUS))==false)
		{
			request.setAttribute(RequestParam.ERROR, ControllerWarning.NOT_ENOUGH_RIGHTS);
			return JSPPage.MAIN_PAGE;
		}
		try {
		String name = request.getParameter(RequestParam.NAME);
		String author=  request.getParameter(RequestParam.AUTHOR);
		String specification =  request.getParameter(RequestParam.SPECIFICATION);
		String ammount = request.getParameter(RequestParam.AMMOUNT);
		String description=  request.getParameter(RequestParam.DESCRIPTION);
		Part file = request.getPart(RequestParam.BOOK_FILE);
		String path=request.getServletContext().getRealPath("/");
		BookService serv = ServiceFactory.getInstance().getBookService();
		serv.addBook(name, author, specification, ammount,description,path,file);
		} catch (IOException e) {
			request.setAttribute(RequestParam.ERROR, ControllerWarning.WRONG);
			return JSPPage.PANEL_PAGE;
		} catch (ServletException e) {
			request.setAttribute(RequestParam.ERROR, ControllerWarning.WRONG);
			return JSPPage.PANEL_PAGE;
		} catch (ServiceException e) {
			request.setAttribute(RequestParam.ERROR, e.getMessage());
		}
		return JSPPage.PANEL_PAGE;
	}

}
