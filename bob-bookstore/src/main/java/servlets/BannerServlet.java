package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.*;
import java.sql.*;




/**
 * Servlet implementation class BannerServlet
 */
@WebServlet("/banner")

//This is a simple example of an HTTP Servlet.
public class BannerServlet extends HttpServlet {
 public void doGet(HttpServletRequest request, HttpServletResponse response)
     throws ServletException, IOException {
     output(request, response);
 }

 public void doPost(HttpServletRequest request, HttpServletResponse response)
     throws ServletException, IOException {
     output(request, response);
 }

 private void output(HttpServletRequest request, HttpServletResponse response)
     throws ServletException, IOException {
     PrintWriter out = response.getWriter();

     // then write the data of the response
     out.println("<body  bgcolor=\"#ffffff\">" + "<center>" +
         "<hr> <br> &nbsp;" + "<h1>" +
         "<font size=\"+3\" color=\"#CC0066\">Duke's </font> <img src=\"" +
         request.getContextPath() +
         "/duke.books.gif\" alt=\"Duke holding books\"\">" +
         "<font size=\"+3\" color=\"black\">Bookstore</font>" + "</h1>" +
         "</center>" + "<br> &nbsp; <hr> <br> ");
 }
}
