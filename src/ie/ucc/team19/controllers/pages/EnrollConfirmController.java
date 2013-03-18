package ie.ucc.team19.controllers.pages;

import ie.ucc.team19.controllers.AbstractController;

public class EnrollConfirmController extends AbstractController{

    /**
     *
     */
    public void execute() {
        /*if() {
            
        }*/
        setReturnPage("/enrollConfirm.jsp");
        getRequest().setAttribute("pageTitle", "Confirm Enrollment");
        
        
    }

}
