package ie.ucc.team19.controllers.pages;

import ie.ucc.team19.controllers.AbstractController;

/**
 * Controller class to handle request for the user accounts page.
 * @author Cormac
 */
public class AccountController  extends AbstractController {

    /**
     * Sets return page and title, request scoped. Makes page not
     * cacheable by browser or proxy. Prevents a logged out user from
     * viewing this secure page using browser history or back button.
     */
    public void execute() {
        setReturnPage("/account.jsp");
        request.setAttribute("pageTitle", "Your Account");
        //Solutions for JSP pages and Struts, By Kevin H. Le, JavaWorld.com, 09/27/04
        response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
        response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
        response.setDateHeader("Expires", 0); // Causes the proxy cache to see the page as "stale"
        response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility
    }
}
