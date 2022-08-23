package servlets;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
		//HTTPResponse
		HttpSession session = request.getSession(false);
		
		/* 
		 * request.getSession(false) will return current session if current session exists. If not, it will not create
		 * a new session.
		*/
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
