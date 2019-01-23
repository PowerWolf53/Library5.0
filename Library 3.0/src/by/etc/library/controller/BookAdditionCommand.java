package by.etc.library.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;


import by.etc.library.service.BookService;
import by.etc.library.service.ServiceException;
import by.etc.library.service.ServiceFactory;

public class BookAdditionCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		if("admin".equals(request.getSession().getAttribute(SessionParam.STATUS))==false)
		{
			request.setAttribute(RequestParam.ERROR, "You dont have enough rights");
			return JSPPage.MAIN_PAGE;
		}
		try {
		String name = request.getParameter("name");
		String author=  request.getParameter("author");
		String specification =  request.getParameter("specification");
		String ammount = request.getParameter("ammount");
		String description=  request.getParameter("description");
		Part file = request.getPart("bookfile");
		String path=request.getParameter("folder");
		BookService serv = ServiceFactory.getInstance().getBookService();
		serv.addBook(name, author, specification, ammount,description,path,file);
		} catch (IOException e) {
			request.setAttribute(RequestParam.ERROR, "Something went wrong");
			return JSPPage.PANEL_PAGE;
		} catch (ServletException e) {
			request.setAttribute(RequestParam.ERROR, "Something went wrong");
			return JSPPage.PANEL_PAGE;
		} catch (ServiceException e) {
			request.setAttribute(RequestParam.ERROR, e.getMessage());
		}
		return JSPPage.PANEL_PAGE;
	}

}
