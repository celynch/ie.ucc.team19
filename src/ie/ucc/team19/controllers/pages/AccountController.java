package ie.ucc.team19.controllers.pages;

import ie.ucc.team19.controllers.AbstractController;

public class AccountController  extends AbstractController {

    public void execute() {

        setReturnPage("/account.jsp");
        getRequest().setAttribute("pageTitle", "Your Account | UCC Summer Courses");
    }
}
