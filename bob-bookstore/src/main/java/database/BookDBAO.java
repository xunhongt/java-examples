/*
 * Copyright (c) 2006 Sun Microsystems, Inc.  All rights reserved.  U.S.
 * Government Rights - Commercial software.  Government users are subject
 * to the Sun Microsystems, Inc. standard license agreement and
 * applicable provisions of the FAR and its supplements.  Use is subject
 * to license terms.
 *
 * This distribution may include materials developed by third parties.
 * Sun, Sun Microsystems, the Sun logo, Java and J2EE are trademarks
 * or registered trademarks of Sun Microsystems, Inc. in the U.S. and
 * other countries.
 *
 * Copyright (c) 2006 Sun Microsystems, Inc. Tous droits reserves.
 *
 * Droits du gouvernement americain, utilisateurs gouvernementaux - logiciel
 * commercial. Les utilisateurs gouvernementaux sont soumis au contrat de
 * licence standard de Sun Microsystems, Inc., ainsi qu'aux dispositions
 * en vigueur de la FAR (Federal Acquisition Regulations) et des
 * supplements a celles-ci.  Distribue par des licences qui en
 * restreignent l'utilisation.
 *
 * Cette distribution peut comprendre des composants developpes par des
 * tierces parties. Sun, Sun Microsystems, le logo Sun, Java et J2EE
 * sont des marques de fabrique ou des marques deposees de Sun
 * Microsystems, Inc. aux Etats-Unis et dans d'autres pays.
 */


package database;

import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import java.util.*;
import exception.*;
import cart.*;

// The instance of BookDBAO gets created when the application
// is deployed. It maintains the Connection object to the
// database. The Connection object is created from DataSource
// object, which is retrieved through JNDI.
// For more information on DataSource, please see
// http://java.sun.com/j2se/1.4.2/docs/api/javax/sql/DataSource.html.
public class BookDBAO {
    private ArrayList books;
    Connection con;
    private boolean conFree = true;
    
    // Database configuration
    public static String url = "jdbc:mysql://localhost:3306/test";
    public static String dbdriver = "com.mysql.jdbc.Driver";
    public static String username = "root";
    public static String password = "P@ssw0rd123";
    
    public BookDBAO() throws Exception {
        try {
            /*
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("jdbc/BookDB");
            con = ds.getConnection();
            */
            Class.forName(dbdriver);
            con = DriverManager.getConnection(url, username, password);
            
        } catch (Exception ex) {
            System.out.println("Exception in BookDBAO: " + ex);
            throw new Exception("Couldn't open connection to database: " +
                    ex.getMessage());
        }
    }
    
    public void remove() {
        try {
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    protected synchronized Connection getConnection() {
        while (conFree == false) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        
        conFree = false;
        notify();
        
        return con;
    }
    
    protected synchronized void releaseConnection() {
        while (conFree == true) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        
        conFree = true;
        notify();
    }
    
    public List getBooks() throws BooksNotFoundException {
        books = new ArrayList();
        
        try {
            String selectStatement = "select * " + "from books";
            getConnection();
            
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            ResultSet rs = prepStmt.executeQuery();
            
            while (rs.next()) {
                BookDetails bd =
                        new BookDetails(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getFloat(5),
                        rs.getBoolean(6), rs.getInt(7), rs.getString(8),
                        rs.getInt(9));
                
                if (rs.getInt(9) > 0) {
                    books.add(bd);
                }
            }
            
            prepStmt.close();
        } catch (SQLException ex) {
            throw new BooksNotFoundException(ex.getMessage());
        }
        
        releaseConnection();
        Collections.sort(books);
        
        return books;
    }
    
    public BookDetails getBookDetails(String bookId)
    throws BookNotFoundException {
        try {
            String selectStatement = "select * " + "from books where id = ? ";
            getConnection();
            
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, bookId);
            
            ResultSet rs = prepStmt.executeQuery();
            
            if (rs.next()) {
                BookDetails bd =
                        new BookDetails(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getFloat(5),
                        rs.getBoolean(6), rs.getInt(7), rs.getString(8),
                        rs.getInt(9));
                prepStmt.close();
                releaseConnection();
                
                return bd;
            } else {
                prepStmt.close();
                releaseConnection();
                throw new BookNotFoundException("Couldn't find book: " +
                        bookId);
            }
        } catch (SQLException ex) {
            releaseConnection();
            throw new BookNotFoundException("Couldn't find book: " + bookId +
                    " " + ex.getMessage());
        }
    }
    
    public void buyBooks(ShoppingCart cart) throws OrderException {
        Collection items = cart.getItems();
        Iterator i = items.iterator();
        
        try {
            getConnection();
            con.setAutoCommit(false);
            
            while (i.hasNext()) {
                ShoppingCartItem sci = (ShoppingCartItem) i.next();
                BookDetails bd = (BookDetails) sci.getItem();
                String id = bd.getBookId();
                int quantity = sci.getQuantity();
                buyBook(id, quantity);
            }
            
            con.commit();
            con.setAutoCommit(true);
            releaseConnection();
        } catch (Exception ex) {
            try {
                con.rollback();
                releaseConnection();
                throw new OrderException("Transaction failed: " +
                        ex.getMessage());
            } catch (SQLException sqx) {
                releaseConnection();
                throw new OrderException("Rollback failed: " +
                        sqx.getMessage());
            }
        }
    }
    
    public void buyBook(String bookId, int quantity) throws OrderException {
        try {
            String selectStatement = "select * " + "from books where id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, bookId);
            
            ResultSet rs = prepStmt.executeQuery();
            
            if (rs.next()) {
                int inventory = rs.getInt(9);
                prepStmt.close();
                
                if ((inventory - quantity) >= 0) {
                    String updateStatement =
                            "update books set inventory = inventory - ? where id = ?";
                    prepStmt = con.prepareStatement(updateStatement);
                    prepStmt.setInt(1, quantity);
                    prepStmt.setString(2, bookId);
                    prepStmt.executeUpdate();
                    prepStmt.close();
                } else {
                    throw new OrderException("Not enough of " + bookId +
                            " in stock to complete order.");
                }
            }
        } catch (Exception ex) {
            throw new OrderException("Couldn't purchase book: " + bookId +
                    ex.getMessage());
        }
    }
}
