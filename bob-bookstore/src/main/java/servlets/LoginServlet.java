package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import database.*;

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

	    String id = request.getParameter("id");
	    String password = request.getParameter("password");
	    
	    boolean result = false ;
	    //ensure that out.close() do not come before response object
	    
	    try {
		    AccountDBAO account = new AccountDBAO();
		    result = account.authenticate(id, password);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    
	    if (result){
		    request.getRequestDispatcher("/bookstore").forward(request,response);
		    return;
	    } else {
		    response.sendRedirect("login.jsp");
		    return;
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
