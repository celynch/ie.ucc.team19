package ie.ucc.team19.controllers.pages;

import ie.ucc.team19.controllers.AbstractController;

public class AdminController extends AbstractController{

    /**
     *
     */
    public void execute() {
        setReturnPage("/admin.jsp");
        getRequest().setAttribute("pageTitle", "TinyMCE TEST | UCC Summer Courses");
    }
}
