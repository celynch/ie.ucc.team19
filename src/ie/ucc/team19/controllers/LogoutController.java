package ie.ucc.team19.controllers;

//import ie.ucc.team19.daos.FooDao;
//import java.util.List;

/**
 *
 * @author Cormac
 */
public class LogoutController extends AbstractController{

    public void execute() {
        this.getRequest().getSession().invalidate();
        this.setReturnPage("/logout.jsp");
        this.getRequest().setAttribute("pageTitle", "Logged Out | UCC Summer Courses");
    }
}
