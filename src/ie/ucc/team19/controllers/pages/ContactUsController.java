package ie.ucc.team19.controllers.pages;

import ie.ucc.team19.controllers.AbstractController;
import ie.ucc.team19.dao.DBConnectionManager;
import ie.ucc.team19.service.SendEmail;

public class ContactUsController extends AbstractController{
    public void execute() {
        setReturnPage("/contactUs.jsp");
        getRequest().setAttribute("pageTitle", "Contact Us");
        
        if(request.getParameter("submit") != null) {
            String studentId = request.getParameter("studentId");
            String subject = request.getParameter("subject");
            String messageText = request.getParameter("messageText");
            new SendEmail(new DBConnectionManager()).submitComment(studentId, subject, messageText);
        }
    }
}
