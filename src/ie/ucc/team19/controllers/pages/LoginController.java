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
        request.setAttribute("pageTitle", "Login");

        String referer = request.getHeader("referer");
        String returnPage = request.getAttribute("serverName") + "/team19/pages/";
        returnPage = returnPage.replace("http:", "https:");
        returnPage = returnPage.startsWith("https://") ? returnPage : "https://" + returnPage;
        returnPage = returnPage.replace("8080", "8443");
        if(referer!=null) {
            int domainStart = referer.indexOf("//") + 2;
            referer = referer.substring(domainStart, referer.indexOf("/", domainStart));
            if(referer.equals(request.getServerName())) {
                returnPage = request.getHeader("referer");
                returnPage = returnPage.replace("http:", "https:");
                returnPage = returnPage.replace("8080", "8443");
            }
        }
        System.out.println(returnPage);
        request.setAttribute("returnURL", returnPage);
    }
}
