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


package listeners;

import database.BookDBAO;
import javax.servlet.*;
import util.Counter;

// Event handler class for handling application scope events
public final class ContextListener implements ServletContextListener {
    private ServletContext context = null;

    // This method gets called when the application is deployed
    public void contextInitialized(ServletContextEvent event) {
        context = event.getServletContext();

        // Create BookDBAO object and save it as an attribute to
        // ServletContext scope object.
        try {
            BookDBAO bookDB = new BookDBAO();
            context.setAttribute("bookDB", bookDB);
        } catch (Exception ex) {
            System.out.println("Couldn't create bookstore database bean: " +
                ex.getMessage());
        }

        // Save hitCounter and orderCounter attributes in the
        // ServletContext scope object
        Counter counter = new Counter();
        context.setAttribute("hitCounter", counter);
        counter = new Counter();
        context.setAttribute("orderCounter", counter);
    }

    // This method gets called when the application is undeployed
    public void contextDestroyed(ServletContextEvent event) {
        context = event.getServletContext();

        BookDBAO bookDB = (BookDBAO) context.getAttribute("bookDB");

        if (bookDB != null) {
            bookDB.remove();
        }

        context.removeAttribute("bookDB");
        context.removeAttribute("hitCounter");
        context.removeAttribute("orderCounter");
    }
}
