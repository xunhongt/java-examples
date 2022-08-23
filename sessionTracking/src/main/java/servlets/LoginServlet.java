package servlets;

import java.io.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession(true);
		
		//HTTPResponse
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		//Referer-Identify the referer
		String referer = request.getHeader("referer");

		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		
		if (id != "" && password != "" ){

			try {
				//AccountDBAO db = new AccountDBAO();
				boolean status = true ;
				
				if (status) {
				//store the user id value into session
					session.setAttribute("id", id);
					
					request.getRequestDispatcher("MainScreenServlet").forward(request,response);
					
					/*
					 * Difference between forward() and include(): 
					 *   - forward() will CLOSE the output stream after it has been invoked 
					 *   - include() will leave the output stream open 
					 *   
					 *   e.g. if you call forward(), it will NOT print HELLO WORLD. But if you call include(), it will 
					 *   print HELLO WORLD
					 *   
					 *   - include() is used to load contents of specific resource directly into servlet's response
					 *     e.g. servlet, JSP, static resource
					 *   - forward() is used for server-side redirection
					 *     e.g. HTTP request for one servlet is routed to another resource. 
					 */
					
					out.println("<h1>HELLO WORLD</h1>");
					out.close();
					
				} 
			}catch (Exception e) {
				e.printStackTrace();
			}
		}else { 
			response.sendRedirect(request.getContextPath() + "/loginFail.jsp");
			
			/* 
			 * In order to call a JSP file from a servlet, remember to call request.getContextPath() 
			 */
		}
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
