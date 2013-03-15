package ie.ucc.team19.controllers.pages;

import ie.ucc.team19.controllers.AbstractController;
import ie.ucc.team19.dao.StudentBean;
import ie.ucc.team19.service.FetchBean;
import ie.ucc.team19.service.LoginUser;
import ie.ucc.team19.service.UpdateUser;

public class PasswordResetController extends AbstractController {
    public void execute() {
        String email = getRequest().getParameter("email");
        String authString = getRequest().getParameter("authString");
        String passwordHash = getRequest().getParameter("passwordHash");
        String passwordHash2 = getRequest().getParameter("passwordHash2");
        StudentBean student = new FetchBean().getStudentByEmail(email);

        if( !passwordHash.equals(passwordHash2)) { // re-entered password matched
            String resubmit = "?authString=" + authString + "&email=" + email;
            setReturnPage("/pages/passwordResetVerify.jsp" + resubmit);
            getRequest().setAttribute("pageTitle", "Complete Password Reset | UCC Summer Courses");
            getRequest().setAttribute("passwordError", true);
        } else if(student.getEmail() == null) { // user not found
            String resubmit = "?authString=" + authString;
            setReturnPage("/pages/passwordResetVerify.jsp" + resubmit);
            getRequest().setAttribute("pageTitle", "Complete Password Reset | UCC Summer Courses");
            getRequest().setAttribute("emailError", true);
        } else if( !student.getAuthString().equals(authString)) { // authString doesn't match
            setReturnPage("/");
            getRequest().setAttribute("pageTitle", "Welcome | UCC Summer Courses");
        } else { // will update password, log user in
            this.getRequest().getSession().setAttribute("user", student);
            student.setPasswordHash(passwordHash2);
            new UpdateUser().updatePasswordHash(student.getEmail(), student.getPasswordHash());
            new LoginUser().setCookies(getResponse(), student,
                    Boolean.parseBoolean(getRequest().getParameter("rememberMe")), false);
        }
    }
}
