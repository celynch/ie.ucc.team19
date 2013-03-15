package ie.ucc.team19.controllers.pages;

import ie.ucc.team19.controllers.AbstractController;

/**
 *
 * @author Cormac
 */
public class PasswordResetRequestController extends AbstractController{

    /**
     *
     */
    public void execute() {
        setReturnPage("/passwordResetRequest.jsp");
        getRequest().setAttribute("pageTitle", "Password Reset | UCC Summer Courses");
    }
}