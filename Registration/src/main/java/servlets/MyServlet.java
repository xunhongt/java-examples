package servlets;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
  //MySerlvet.java doGet method
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// Set the response message's MIME type
    	response.setContentType("text/html;charset=UTF-8");
    
		// Allocate a output writer to write the response message into the network socket
    	PrintWriter out = response.getWriter();
    
    	// Write the response message, in an HTML page
    	try {
		    out.println("<!DOCTYPE html>");
		    out.println("<html><head>");

		    out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");

		    out.println("<title>Hello, World</title></head>");
		    out.println("<body>");
		    out.println("<h1>Hello, world!</h1>"); // says Hello
    
		    // Extract Form data
		    out.println("<p>Username: " + request.getParameter("username") + "</p>");
		    out.println("<p>Gender: " + request.getParameter("gender") + "</p>");
		    out.println("<p>Year of study : " + request.getParameter("study_year") + "</p>");
    
		    // Echo client's request information
		    out.println("<p>Request URI: " + request.getRequestURI() + "</p>");
		    out.println("<p>Protocol: " + request.getProtocol() + "</p>");
		    out.println("<p>PathInfo: " + request.getPathInfo() + "</p>");
		    out.println("<p>Remote Address: " + request.getRemoteAddr() + "</p>");
		    
		    // Generate a random number upon each request
		    out.println("<p>A Random Number: <strong>" + Math.random() + "</strong></p>");
		    out.println("<hr/>");
		    out.println("</body>");
		    out.println("</html>");
    	} finally {
	    out.close(); // Always close the output writer
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
