package ie.ucc.team19.controllers;

import ie.ucc.team19.controllers.Controller;
import ie.ucc.team19.controllers.ControllerFactory;
import ie.ucc.team19.dao.DBConnectionManager;
import ie.ucc.team19.dao.StudentBean;
import ie.ucc.team19.service.LoginUser;
import ie.ucc.team19.service.ScheduledTask;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * MVC Model 2 Front Controller implementation. Responsible for
 * dispatching requests to appropriate controller classes. Also performs
 * common controller setup tasks. 
 * @author Cormac
 */
public class FrontController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ArrayList<ScheduledTask> tasks = new ArrayList<ScheduledTask>();

    /**
     * Called on instantiation. Sets up the servlet instance. Initiates
     * scheduled task timers.
     */
    @Override
    public void init() throws ServletException {
        super.init();
        int milisPerDay = 1000*60*60*24;
        int milisPerHour = 1000*60*60;
        new ScheduledTask(milisPerDay, "expireUnverifiedStudents", new DBConnectionManager());
        new ScheduledTask(milisPerHour * 2, "expirePendingEnrolls", new DBConnectionManager());
    }

    /**
     * Reacts to a HTTP request, performs session management tasks and
     * passes the request and response to a controller determined by the
     * request URL pattern.
     * @param request - implicit object to encapsulate the HTTP request.
     * @param response - implicit object to encapsulate the HTTP response.
     * @throws ServletException
     * @throws IOException
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        setServerNames(request);
        StudentBean student = (StudentBean) request.getSession().getAttribute("user");
        manageSession(student, request, response);

        try {
            String controller = getURLPattern(request);
            //Instantiate controller class
            Controller control = ControllerFactory.getControllerByFullClassName(controller);
            control.init(request, response);
            control.execute();
            response.setContentType("text/html;charset=UTF-8");
            RequestDispatcher requestDispatcher
                    = getServletContext().getRequestDispatcher(control.getReturnPage());
            requestDispatcher.forward(request, response);
        } catch (Exception e) {
            System.out.println("Error determining request controller.");
            e.printStackTrace();
        }
    }

    /**
     * Determines the state of the session: whether the user is logged in,
     * attempting login via form, login to verify new/reset account, or
     * possesses a valid login cookie. 
     * @param student - Bean session attribute for logged in user.
     * @param request - implicit object to encapsulate the HTTP request.
     * @param response - implicit object to encapsulate the HTTP response.
     */
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

    /**
     * Called when request method is 'get'.
     * @see processRequest
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Called when request method is 'post'.
     * @see processRequest
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Parses request URL to determine pattern for controller to handle
     * request.
     * @param request - implicit object to encapsulate the HTTP request.
     * @return - String URL pattern prefix for controller type.
     */
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

    /**
     * Sets as a request scoped reference the server name with or without
     * port number suffix.
     * @param request - implicit object to encapsulate the HTTP request.
     */
    private void setServerNames(HttpServletRequest request) {
        String serverName= request.getServerName();
        String secureServerName = serverName.equals("localhost") ? serverName + ":8443" : serverName;
        serverName = serverName.equals("localhost") ? serverName + ":8080" : serverName;
        request.setAttribute("serverName",serverName);
        request.setAttribute("secureServerName",secureServerName);
    }

    /**
     * Called on application shutdown. Performs clean up tasks, releases
     * resources, cancels scheduled tasks.
     */
    @Override
    public void destroy() {
        super.destroy();
        for(ScheduledTask task : tasks) {
            task.stop();
        }
    }
}
