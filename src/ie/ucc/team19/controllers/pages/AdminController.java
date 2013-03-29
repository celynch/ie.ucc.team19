package ie.ucc.team19.controllers.pages;

import ie.ucc.team19.controllers.AbstractController;

public class AdminController extends AbstractController{

    /**
     * Fetches beans from model for display in dashboard view.
     */
    public void execute() {
        setReturnPage("/admin.jsp");
        request.setAttribute("pageTitle", "Admin Access");
        request.setAttribute("admin", true);
    }
}
