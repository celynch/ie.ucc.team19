package ie.ucc.team19.controllers.pages;

import ie.ucc.team19.controllers.AbstractController;
import ie.ucc.team19.dao.DBConnectionManager;
import ie.ucc.team19.dao.StudentBean;
import ie.ucc.team19.service.FetchBeanUtils;
import ie.ucc.team19.service.LoginUser;
import ie.ucc.team19.service.PropertiesReader;
import ie.ucc.team19.service.UpdateUser;

public class PasswordResetController extends AbstractController {
    public void execute() {
        PropertiesReader properties = (PropertiesReader)
            request.getSession().getServletContext().getAttribute("properties");
        DBConnectionManager connector = new DBConnectionManager(properties);
        FetchBeanUtils fetcher = new FetchBeanUtils(connector);
        
        String email = request.getParameter("email");
        String authString = request.getParameter("authString");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        StudentBean student = fetcher.getStudentByEmail(email);

        if( !password.equals(password2)) { // re-entered passwords don't match
            String resubmit = "?authString=" + authString + "&email=" + email;
            setReturnPage("/pages/passwordResetVerify.jsp" + resubmit);
            request.setAttribute("pageTitle", "Complete Password Reset");
            request.setAttribute("passwordError", true);
        } else if(student.getEmail() == null) { // user not found
            String resubmit = "?authString=" + authString;
            setReturnPage("/pages/passwordResetVerify.jsp" + resubmit);
            request.setAttribute("pageTitle", "Complete Password");
            request.setAttribute("emailError", true);
        } else if( !student.getAuthString().equals(authString)) { // authString doesn't match
            setReturnPage("/");
            request.setAttribute("pageTitle", "Welcome");
        } else { // will update password, log user in
            request.getSession().setAttribute("user", student);
            student.setPassword(password2);
            new UpdateUser(connector).updatePasswordHash(student.getEmail(), student.getPassword());
            new LoginUser(connector).setCookies(response, student, request.getServerName(),
                    Boolean.parseBoolean(request.getParameter("rememberMe")), false);
        }
    }
}
