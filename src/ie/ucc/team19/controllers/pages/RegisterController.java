package ie.ucc.team19.controllers.pages;

import java.util.Calendar;

import ie.ucc.team19.controllers.AbstractController;

/**
 * 
 * @author Cormac
 *
 */
public class RegisterController extends AbstractController{
    public void execute() {
        setReturnPage("/register.jsp");
        request.setAttribute("year", Calendar.getInstance().get(Calendar.YEAR));
        request.setAttribute("pageTitle", "Account Registration");
    }
}
