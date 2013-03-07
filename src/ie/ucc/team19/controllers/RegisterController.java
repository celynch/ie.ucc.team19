package ie.ucc.team19.controllers;

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
