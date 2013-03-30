package ie.ucc.team19.controllers.pages;

import java.util.Calendar;

import ie.ucc.team19.controllers.AbstractController;

/**
 * Controller for display of registration form.
 * @author Cormac
 */
public class RegisterController extends AbstractController{
    /**
     * Gets the current year, passes to the view to set an age limit in
     * registration form.
     */
    public void execute() {
        setReturnPage("/register.jsp");
        request.setAttribute("year", Calendar.getInstance().get(Calendar.YEAR));
        request.setAttribute("pageTitle", "Account Registration");
    }
}
