package by.etc.library.controller;


import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





/**
 * Servlet implementation class AuthorisationServlet
 */
@WebServlet("/AuthorisationServlet")
public class AuthorisationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthorisationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		ComandManager manager=ComandManager.getInstance();
		Command com =manager.getCommand(request.getParameter("command"));
		String page=com.execute(request);
		
		System.out.println(request.getSession().getAttribute("status"));
		
		
		request.getRequestDispatcher(page).forward(request, response);
		
		
		
	}

}
