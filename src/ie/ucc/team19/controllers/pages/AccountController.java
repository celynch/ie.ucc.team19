package ie.ucc.team19.controllers.pages;

import ie.ucc.team19.controllers.AbstractController;

public class AccountController  extends AbstractController {

    public void execute() {
        setReturnPage("/account.jsp");
        getRequest().setAttribute("pageTitle", "Your Account");
        //Solutions for JSP pages and Struts, By Kevin H. Le, JavaWorld.com, 09/27/04
        getResponse().setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
        getResponse().setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
        getResponse().setDateHeader("Expires", 0); // Causes the proxy cache to see the page as "stale"
        getResponse().setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility
    }
}
