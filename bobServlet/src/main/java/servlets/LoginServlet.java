package servlets;

import java.io.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

			// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		if (id != "" && password != "" ){
			
			if (id.equals(password)){
				
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				
				out.println ("<html>");
				out.println ("<head>");
				out.println ("<title>Response from LoginServlet</title>");
				out.println ("</head>");
				out.println ("<body>");
				out.println ("<h1>Hello World!</h1>");
				out.println ("<h2>My name is Bob! Nice to meet you :)</h2>");
				out.println ("</body>");
				out.println ("</html>");
				out.close();
			}
			else {
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				
				out.println ("<html>");
				out.println ("<head>");
				out.println ("<title>Response from LoginServlet</title>");
				out.println ("</head>");
				out.println ("<body>");
				out.println ("<h1>Your Username and Password does not match!</h1>");
				out.println ("<h2>Please try again...</h2>");
				out.println ("</body>");
				out.println ("</html>");
				out.close();
				
				System.out.println("login failure");
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
