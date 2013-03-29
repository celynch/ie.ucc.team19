package ie.ucc.team19.controllers.pages;

import ie.ucc.team19.controllers.AbstractController;
import ie.ucc.team19.dao.DBConnectionManager;
import ie.ucc.team19.service.PropertiesReader;
import ie.ucc.team19.service.SendEmail;

/**
 * Controller class to handle request for 'contact us' page.
 * @author Cormac
 */
public class ContactUsController extends AbstractController{
    /**
     * Comments submitted on this page are added to the database for admin
     * review. 
     */
    public void execute() {
        PropertiesReader properties = (PropertiesReader)
                request.getSession().getServletContext().getAttribute("properties");
        DBConnectionManager connector = new DBConnectionManager(properties);
        setReturnPage("/contactUs.jsp");
        request.setAttribute("pageTitle", "Contact Us");

        if(request.getParameter("submit") != null) {
            String studentId = request.getParameter("studentId");
            String subject = request.getParameter("subject");
            String messageText = request.getParameter("messageText");
            new SendEmail(connector).submitComment(
                    studentId, subject, messageText);
        }
    }
}
