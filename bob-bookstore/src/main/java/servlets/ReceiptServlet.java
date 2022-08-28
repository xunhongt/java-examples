package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cart.ShoppingCart;
import database.BookDBAO;
import exception.OrderException;

/**
 * Servlet implementation class ReceiptServlet
 */
@WebServlet("/bookreceipt")
public class ReceiptServlet extends HttpServlet {
    private BookDBAO bookDB;

    public void init() throws ServletException {
        bookDB = (BookDBAO) getServletContext()
                                .getAttribute("bookDB");

        if (bookDB == null) {
            throw new UnavailableException("Couldn't get database.");
        }
    }

    public void destroy() {
        bookDB = null;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        boolean orderCompleted = true;

        // Get the user's session and shopping cart
        HttpSession session = request.getSession(true);
        ResourceBundle messages =
            (ResourceBundle) session.getAttribute("messages");
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");

        if (cart == null) {
            cart = new ShoppingCart();
            session.setAttribute("cart", cart);
        }

        // Update the inventory
        try {
            bookDB.buyBooks(cart);
        } catch (OrderException e) {
            System.err.println(e.getMessage());
            orderCompleted = false;
        }

        // Payment received -- invalidate the session
        session.invalidate();

        // set content type header before accessing the Writer
        response.setContentType("text/html");
        response.setBufferSize(8192);

        PrintWriter out = response.getWriter();

        // then write the response
        out.println("<html>" + "<head><title>" +
            messages.getString("TitleReceipt") + "</title></head>");

        // Get the dispatcher; it gets the banner to the user
        RequestDispatcher dispatcher =
            getServletContext()
                .getRequestDispatcher("/banner");

        if (dispatcher != null) {
            dispatcher.include(request, response);
        }

        if (orderCompleted) {
            out.println("<h3>" + messages.getString("ThankYou") +
                request.getParameter("cardname") + ".");
        } else {
            out.println("<h3>" + messages.getString("OrderError"));
        }

        out.println("<p> &nbsp; <p><strong><a href=\"" +
            response.encodeURL(request.getContextPath()) + "/bookstore\">" +
            messages.getString("ContinueShopping") +
            "</a> &nbsp; &nbsp; &nbsp;" + "</body></html>");
        out.close();
    }

    public String getServletInfo() {
        return "The Receipt servlet updates the book database inventory, invalidates the user session, " +
        "thanks the user for the order.";
    }
}
