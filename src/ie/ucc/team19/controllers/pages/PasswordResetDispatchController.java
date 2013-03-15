package ie.ucc.team19.controllers.pages;

import ie.ucc.team19.controllers.AbstractController;
import ie.ucc.team19.dao.*;
import ie.ucc.team19.service.*;

import java.util.UUID;

public class PasswordResetDispatchController extends AbstractController{
    public void execute() {
        String email = getRequest().getParameter("email");
        StudentBean student = new FetchBean().getStudentByEmail(email);
        if(student == null) {
            setReturnPage("/pages/passwordResetRequest");
            getRequest().setAttribute("emailError", true);
        } else {
            student.setAuthString(UUID.randomUUID().toString());
            new UpdateUser().updateAuthString(email, student.getAuthString());
            setReturnPage("/");
            getRequest().setAttribute("pageTitle", "Welcome | UCC Summer Courses");
    
            String subject = "UCC Summer Courses | Password Reset";
            String mailMessage = "<div><p>" + student.getFirstName()
                    + ", if you requested this password reset, follow this link:</p>"
                    + "<a href=\"http://localhost:8080/team19/pages/passwordResetVerify?authString="
                    + student.getAuthString() + "&email=" + student.getEmail() + "\">Reset Password</a>"
                    + "<blockquote>\"Where Finbarr taught, let Munster learn\"</blockquote>"
                    + "<p>- The UCC Summer Courses Team</p></div>";
            new SendEmail().sendEmail( student.getEmail(), subject, mailMessage);
        }
    }
}
