package ie.ucc.team19.controllers;

import ie.ucc.team19.controllers.Controller;
import ie.ucc.team19.controllers.ControllerFactory;
import ie.ucc.team19.dao.DBConnectionManager;
import ie.ucc.team19.dao.UserBean;
import ie.ucc.team19.service.LoginUser;
import ie.ucc.team19.service.PropertiesReader;

import java.io.IOException;
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

    /**
     * Called on instantiation. Sets up the servlet instance. Initiates
     * scheduled task timers.
     */
    @Override
    public void init() throws ServletException {
        super.init();
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
        UserBean user = (UserBean) request.getSession().getAttribute("user");
        manageSession(user, request, response);

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
    private void manageSession(UserBean user, HttpServletRequest request, HttpServletResponse response) {
        if( (user == null) || ( user.getUniqueId() == null) )  {
            PropertiesReader properties = (PropertiesReader)
                    request.getSession().getServletContext().getAttribute("properties");
            DBConnectionManager connector = new DBConnectionManager(properties);
            LoginUser logger = new LoginUser(connector);
            if(request.getParameter("studentLogin") != null) {// standard login via banner
                logger.loginViaForm(request, response);
            } else if(request.getParameter("loginVerify") != null) {
                if (request.getParameter("authString") != null) {
                    logger.loginVerify(request, response);// verification login
                } else {
                    logger.loginViaForm(request, response);// challenge login
                }
            } else if (request.getParameter("adminLogin") != null) {
                logger.loginAdminForm(request, response);//admin Login
            } else {        // cookie login
                logger.loginViaCookie(request, response);
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
     * resources.
     */
    @Override
    public void destroy() {
        super.destroy();
    }
}
