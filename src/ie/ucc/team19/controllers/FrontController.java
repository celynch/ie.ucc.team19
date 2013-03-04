package ie.ucc.team19.controllers;

import ie.ucc.team19.controllers.Controller;
import ie.ucc.team19.controllers.ControllerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class FrontController extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Override
    public void init() throws ServletException {
        super.init();


    }

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            //get the request's url
            String url = request.getRequestURL().substring(request.getRequestURL().lastIndexOf("/") + 1, request.getRequestURL().length());
            //get Controllers name from request
            String controller = url.substring(0, url.indexOf("."));
            //Instantiate controller class
            Controller control = ControllerFactory.getControllerByFullClassName(controller);
            //initialize controller
            control.init(request);
            
            control.execute();
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(control.getReturnPage());
            requestDispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
