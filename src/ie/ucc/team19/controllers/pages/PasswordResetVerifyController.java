package ie.ucc.team19.controllers.pages;

import ie.ucc.team19.controllers.AbstractController;

public class PasswordResetVerifyController extends AbstractController {
    public void execute() {
        setReturnPage("/passwordResetVerify.jsp");
        request.setAttribute("pageTitle", "Complete Password Reset");
    }
}
