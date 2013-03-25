package ie.ucc.team19.controllers;


import ie.ucc.team19.controllers.Controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Superclass for controller objects. Used to execute application logic,
 * such as selecting the return page to display depending on user session
 * status.
 * 
 * @author Cormac
 */
public abstract class AbstractController implements Controller {

    protected HttpServletResponse response;
    protected HttpServletRequest request;
    protected String returnPage;

    /**
     * Called on creation. Used to make request and response implicit
     * objects accesible to concrete subclass controllers.
     */
    public void init(HttpServletRequest request, HttpServletResponse response) {
        this.setRequest(request);
        this.setResponse(response);
    }

    /**
     * Sets the return page URL for the RequestDispatcher.
     * @param page - the (jsp) page to return.
     */
    public void setReturnPage(String page) {
        returnPage = page;
    }

    /**
     * Returns the return page URL. Called by the FrontController.
     * @return String - the (jsp) page to return.
     */
    public String getReturnPage() {
        return returnPage;
    }

    /**
     * Returns the implicit request object for the request which invoked
     * this instance
     * @return - the implicit request object
     */
    public HttpServletRequest getRequest() {
        return request;
    }

    /**
     * Sets the implicit request object for the request which invoked
     * @param request - the implicit request object
     */
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }
    
    /**
     * Returns the implicit response object for the request which invoked
     * this instance
     * @return - the implicit response object
     */
    public HttpServletResponse getResponse() {
        return response;
    }

    /**
     * Returns the implicit response object for the request which invoked
     * this instance
     * @param response - the implicit response object
     */
    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }
}
