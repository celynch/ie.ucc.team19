package ie.ucc.team19.controllers.pages;

import ie.ucc.team19.controllers.AbstractController;
import ie.ucc.team19.dao.*;
import ie.ucc.team19.service.*;

import java.util.UUID;

public class ResetPasswordController extends AbstractController{
    public void execute() {
        StudentBean student = new FetchBean().getStudentByEmail(this.getRequest().getParameter("email"));
        student.setAuthString(UUID.randomUUID().toString());
        new UpdateUser().updateAuthString(student.getEmail(), student.getAuthString());
        this.setReturnPage("/");
        this.getRequest().setAttribute("pageTitle", "Welcome | UCC Summer Courses");

        String subject = "UCC Summer Courses | Password Reset";
        String mailMessage = student.getFirstName() + ", if you requested this password reset, follow this link:\n "
                + "<a href=\"http://localhost:8080/team19/pages/login?authString="
                + student.getAuthString() + "\">Reset Password</a>\n"
                + "\n<blockquote>\"Where Finbarr taught, let Munster learn\"</blockquote>\n- The UCC Summer Courses Team ";
        new SendEmail().sendEmail( student.getEmail(), subject, mailMessage);
    }
}
