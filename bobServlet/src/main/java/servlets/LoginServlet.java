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

/**
 * 
 * @author thamxunhong
 *  
 * 2022-08-20: 
 *   If you git pull the project, and you face an issue where 
 *   "the import javax.servlet.http cannot be resolved", perform the following:
 *   
 *   - manually build the project (Configure Build Path)
 *   - go to Libraries --> Classpath --> External Jars 
 *   - add servlet-api.jar
 * 
 *   https://stackoverflow.com/questions/4119448/the-import-javax-servlet-cant-be-resolved
 *  
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
    
    /*
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	*/

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		//HTTPSession
		HttpSession session = request.getSession(true);
		
		response.setContentType("text/html");		
		PrintWriter out = response.getWriter();
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		//Referer-Identify the referer
		String referer = request.getHeader("referer");
		
		if (id != "" && password != "" ){
			try {
				//AccountDBAO db = new AccountDBAO();
				//call DAO to verify login and password with DB in later practical
				boolean status = true;
				
				if (status) {
					//store the user id value into session
					session.setAttribute("id", id);
					request.getRequestDispatcher("MainScreenServlet").forward(request,response);
					
				} else {
			        response.sendRedirect(referer);  
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
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
