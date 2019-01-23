package by.etc.library.controller;




import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import by.etc.library.bean.Book;
import by.etc.library.bean.BookOrder;
import by.etc.library.bean.PrivateBook;
import by.etc.library.bean.PrivateBookOrder;
import by.etc.library.bean.ServiceResponse;
import by.etc.library.bean.User;
import by.etc.library.dao.BookDAO;
import by.etc.library.dao.DAOFactory;
import by.etc.library.dao.DaoException;
import by.etc.library.dao.SQLBookDAO;
import by.etc.library.dao.UserDAO;
import by.etc.library.service.BookService;
import by.etc.library.service.ServiceException;
import by.etc.library.service.ServiceFactory;



/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
@MultipartConfig
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookDAO dao=DAOFactory.getInstance().getBookDao();
		try {
		System.out.println(dao.getPrivateBookName(14));
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		System.out.println(request.getParameter("locale"));
	
		
	}

}
