package ie.ucc.team19.controllers.pages;

import ie.ucc.team19.controllers.AbstractController;

/**
 * 
 * @author Cormac
 *
 */
public class RegisterController extends AbstractController{
    public void execute() {
        //System.out.println("here");
        this.setReturnPage("/register.jsp");
        this.getRequest().setAttribute("pageTitle", "Account Registration | UCC Summer Courses");
    }
}
