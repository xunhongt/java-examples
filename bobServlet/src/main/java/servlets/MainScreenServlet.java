package servlets;

import java.io.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;
import java.util.Date;

/**
 * Servlet implementation class MainScreenServlet
 */
@WebServlet("/MainScreenServlet")
public class MainScreenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainScreenServlet() {
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
		
	
			// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String password = request.getParameter("password");
	
		
		if (id != "" && password != "" ){
			try {
				//AccountDBAO db = new AccountDBAO();
				//call DAO to verify login and password with DB in later practical
				
				boolean status = true ;
				
				if (status) {
					
					//retrieve user id from session and display
					out.println("<HTML><BODY>" +
							"<H1 ALIGN=\"CENTER\"> Session Info </H1>" +
							"<TABLE BORDER=1 ALIGN=\"CENTER\">" +
							"<TR>" +
							"<TH>Info Type<TH>Value" +
							"<TR>" +
							" <TD>Session ID (MainScreenServlet)" +
							" <TD>" + session.getId() +
							"<TR>" +
							" <TD>Creation Time" +
							" <TD>" + new Date(session.getCreationTime()) +
							"<TR>" +
							" <TD>Time of Last Access" +
							" <TD>" + new Date(session.getLastAccessedTime()) +
							"<TR>" +
							" <TD>User Id" +
							" <TD>" + session.getAttribute("id") +
							"</TR>"+
							"</TABLE>" +
							"</BODY></HTML>");
					out.close();
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
