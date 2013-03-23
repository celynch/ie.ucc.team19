package ie.ucc.team19.controllers.pages;

import ie.ucc.team19.controllers.AbstractController;

public class ContactUsController extends AbstractController{
    public void execute() {
        setReturnPage("/contactUs.jsp");
        getRequest().setAttribute("pageTitle", "Contact Us");
    }
}
