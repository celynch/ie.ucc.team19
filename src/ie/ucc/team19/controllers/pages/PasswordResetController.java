package ie.ucc.team19.controllers.pages;

import ie.ucc.team19.controllers.AbstractController;

/**
 *
 * @author Cormac
 */
public class PasswordResetController extends AbstractController{

    /**
     *
     */
    public void execute() {
        this.setReturnPage("/passwordReset.jsp");
        this.getRequest().setAttribute("pageTitle", "Password Reset | UCC Summer Courses");
    }
}