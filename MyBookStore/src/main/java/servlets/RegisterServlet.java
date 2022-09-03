package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

import database.*;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
                PrintWriter out = response.getWriter();
		String id = request.getParameter("email");
		String password = request.getParameter("psw");
		boolean result = false ;
		
		try {
			AccountDBAO account = new AccountDBAO();
			result = account.create(id, password);
		}
		catch (Exception e)
		{
		 e.printStackTrace();		
		}		
				
		if (result){
			System.out.print("Registration is successful!");
			out.println("<br> <b>Registration is successful</b>");
			//request.getRequestDispatcher("/bookstore").forward(request,response);
			return;
		}
		else { 		
			response.sendRedirect("Register.jsp");
			return;
		}
		
	}
}	
