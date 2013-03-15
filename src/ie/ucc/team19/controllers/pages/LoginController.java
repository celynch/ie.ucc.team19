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
        getRequest().setAttribute("pageTitle", "Welcome | UCC Summer Courses");
    }
}
