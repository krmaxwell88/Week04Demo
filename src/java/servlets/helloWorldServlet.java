
package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Person;


public class helloWorldServlet extends HttpServlet {
    
    /*
    The doGet method will run when a "GET" request is made to this servlet.
    For example, if someone clicks a link to this servlet, or if the "/hello"
    URL is typed in the address bar of a web browser.
    */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // call JSP to be loaded
        getServletContext().getRequestDispatcher("/WEB-INF/helloWorldForm.jsp")
                .forward(request, response);
    }

    /*
    The doPost method will run when a "POST" request is made to this servlet.
    For example, after a form submission if the form has a method of "POST".
    */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // grab the parameters from the incoming form fields
        String firstname = request.getParameter("first_name");
        String lastname = request.getParameter("last_name");
        
        
        
        // If the form has not been filled out correctly...
        if( firstname == null || firstname.equals("") || lastname == null || lastname.equals("") ){
            
            // set attributes, in this case they will be used in the JSP.
            request.setAttribute("firstName",firstname);
            request.setAttribute("lastName",lastname);
            
            // A helpful message to the user, letting them know what went wrong.
            request.setAttribute("message", "Please enter both a first and last name.");
            //call the form once again, so the user can re-fill the fields
            getServletContext().getRequestDispatcher("/WEB-INF/helloWorldForm.jsp")
                .forward(request, response);
            // return will stop the code call
            // in most cases, you should not be running code after a JSP is called.
            return;
        }

        Person person = new Person(firstname, lastname);
        request.setAttribute("person", person);
        
        // if the form has been filled out correctly, show the sayHello JSP
        getServletContext().getRequestDispatcher("/WEB-INF/sayHello.jsp")
                .forward(request, response);
                        
    }

}
