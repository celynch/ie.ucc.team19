package ie.ucc.team19.controllers.pages;

import ie.ucc.team19.controllers.AbstractController;

/**
 * Controller for password request form display
 * @author Cormac
 */
public class PasswordResetRequestController extends AbstractController{

    /**
     * Displays for for password reset request.
     */
    public void execute() {
        setReturnPage("/passwordResetRequest.jsp");
        request.setAttribute("pageTitle", "Password Reset");
    }
}