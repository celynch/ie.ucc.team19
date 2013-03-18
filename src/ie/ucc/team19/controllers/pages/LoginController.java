package ie.ucc.team19.controllers.pages;

import ie.ucc.team19.controllers.AbstractController;

/**
 *
 * @author Cormac
 */
public class LoginController extends AbstractController{

    /**
     *
     */
    public void execute() {
        setReturnPage("/login.jsp");
        getRequest().setAttribute("pageTitle", "Login");
        String referer = getRequest().getHeader("referer");
        String returnPage = "https://localhost:8443/team19/pages/";
        if(referer!=null) {
            int domainStart = referer.indexOf("//") + 2;
            referer = referer.substring(domainStart, referer.indexOf("/", domainStart));
            if(referer.equals("localhost:8080") || referer.equals("localhost:8443")) {
                returnPage = getRequest().getHeader("referer");
                returnPage = returnPage.replace("http:", "https:");
                returnPage = returnPage.replace("8080", "8443");
            }
        }
        getRequest().setAttribute("returnURL", returnPage);
    }
}
