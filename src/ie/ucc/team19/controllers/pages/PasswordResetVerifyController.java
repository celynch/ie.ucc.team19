package ie.ucc.team19.controllers.pages;

import ie.ucc.team19.controllers.AbstractController;

public class PasswordResetVerifyController extends AbstractController {
    public void execute() {
        this.setReturnPage("/passwordResetVerify.jsp");
        this.getRequest().setAttribute("pageTitle", "Complete Password Reset | UCC Summer Courses");
    }
}
