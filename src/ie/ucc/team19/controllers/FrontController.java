package ie.ucc.team19.controllers;

import ie.ucc.team19.controllers.Controller;
import ie.ucc.team19.controllers.ControllerFactory;
import ie.ucc.team19.dao.StudentBean;
import ie.ucc.team19.service.LoginUser;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * MVC Model 2 Front Controller implementation. Responsible for
 * dispatching requests to appropriate controller classes. Also provides
 * common 
 * @author Cormac
 */
public class FrontController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void init() throws ServletException {
        super.init();
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String serverName= request.getServerName();
        serverName = serverName.equals("localhost") ? serverName + ":8080" : serverName;
        request.setAttribute("serverName",serverName);
        StudentBean student = (StudentBean) request.getSession().getAttribute("user");
        manageSession(student, request, response);

        try {
            String controller = getURLPattern(request);
            //Instantiate controller class
            Controller control = ControllerFactory.getControllerByFullClassName(controller);
            control.init(request, response);
            control.execute();
            response.setContentType("text/html;charset=UTF-8");
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(control.getReturnPage());
            requestDispatcher.forward(request, response);
        } catch (Exception e) {
            System.out.println("Error determining request controller.");
            e.printStackTrace();
        }
    }

    private void manageSession(StudentBean student, HttpServletRequest request, HttpServletResponse response) {
        if( (student == null) || ( student.getFirstName() == null) )  {
            if(request.getParameter("login") != null) {// standard login via banner
                new LoginUser().loginViaForm(request, response);
            } else if(request.getParameter("loginVerify") != null) {
                if (request.getParameter("authString") != null) {
                    new LoginUser().loginVerify(request, response);// verification login
                } else {
                    new LoginUser().loginViaForm(request, response);// challenge login
                }
            } else {        // cookie login
                new LoginUser().loginViaCookie(request, response);
            }
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

    private String getURLPattern(HttpServletRequest request) {
        //get the request's url
        String requestURL = request.getRequestURL().toString();
        String url = requestURL.substring(requestURL.lastIndexOf("/") + 1, requestURL.length());
        //get Controllers name from request
        String controller = url.substring(0, url.indexOf(".") != -1 ? url.indexOf(".") : url.length());
        controller = controller.length() == 0 ? "Index" : controller;
        controller = controller.substring(0,1).toUpperCase() + controller.substring(1);
        return controller;
    }
}
