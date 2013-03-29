package ie.ucc.team19.controllers.pages;

import ie.ucc.team19.controllers.AbstractController;
import ie.ucc.team19.dao.*;
import ie.ucc.team19.service.*;

import java.util.UUID;

public class PasswordResetDispatchController extends AbstractController{
    public void execute() {
        PropertiesReader properties = (PropertiesReader)
                request.getSession().getServletContext().getAttribute("properties");
        DBConnectionManager connector = new DBConnectionManager(properties);
        FetchBeanUtils fetcher = new FetchBeanUtils(connector);
        
        String email = request.getParameter("email");
        StudentBean student = fetcher.getStudentByEmail(email);
        if(student == null) {
            setReturnPage("/pages/passwordResetRequest");
            request.setAttribute("emailError", true);
        } else {
            student.setAuthString(UUID.randomUUID().toString());
            new UpdateUser(connector).updateAuthString(email, student.getAuthString());
            setReturnPage("/");
            request.setAttribute("pageTitle", "Welcome");
    
            String subject = "UCC Summer Courses | Password Reset";
            String mailMessage = "<div><p>" + student.getFirstName()
                    + ", if you requested this password reset, follow this link:</p>"
                    + "<a href=\"http://localhost:8080/team19/pages/passwordResetVerify?authString="
                    + student.getAuthString() + "&email=" + student.getEmail() + "\">Reset Password</a>"
                    + "<blockquote>\"Where Finbarr taught, let Munster learn\"</blockquote>"
                    + "<p>- The UCC Summer Courses Team</p></div>";
            new SendEmail(connector).sendEmail( student.getEmail(), subject, mailMessage);
        }
    }
}
