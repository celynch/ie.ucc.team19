package ie.ucc.team19.controllers.pages;

import ie.ucc.team19.controllers.AbstractController;

/**
 * Controller to present final stage in password reset.
 * @author Cormac
 */
public class PasswordResetVerifyController extends AbstractController {
    /**
     * Displays form for reset verification, with email, password *2 and
     * authorization string in hidden field.
     */
    public void execute() {
        setReturnPage("/passwordResetVerify.jsp");
        request.setAttribute("pageTitle", "Complete Password Reset");
    }
}
