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
      
	private static final String COMMAND="command";
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
		
		ComandManager manager=ComandManager.getInstance();
		Command com =manager.getCommand(request.getParameter(COMMAND));
		String page=com.execute(request);
		
		
		
		
		request.getRequestDispatcher(page).forward(request, response);
		
		
		
	}

}
