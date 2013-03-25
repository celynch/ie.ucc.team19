package ie.ucc.team19.controllers.pages;

import ie.ucc.team19.controllers.AbstractController;
import ie.ucc.team19.dao.*;
import ie.ucc.team19.service.*;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.UUID;
import org.apache.commons.beanutils.BeanUtilsBean;

public class RegisterCompleteController extends AbstractController{
    public void execute() {
        DBConnectionManager connector = new DBConnectionManager();
        StudentBean user = setupStudent();
        new InsertData(connector).createStudent(user);
        setReturnPage("/registerComplete.jsp");
        request.setAttribute("pageTitle", "Registration Completed");
        String subject = "UCC Summer Courses | Welcome";
        String mailMessage = "<div><p>Thanks for joining UCC Summer Courses.</p>"
                + "<p>We listed your sign in details below, make sure you keep them safe.</p>"
                + "<p>To verify your email address, please follow this link:"
                + "<a href=\"http://localhost:8080/team19/pages/login"
                + "?authString=" + user.getAuthString()
                + "&email=" + user.getEmail() +"\">Complete Registration</a></p>"
                + "<p>Your email address: " + user.getEmail() + "</p>"
                + "<blockquote>\"Where Finbarr taught, let Munster learn\"</blockquote>"
                + "<p>- The UCC Summer Courses Team</p></div>";
        new SendEmail(connector).sendEmail( user.getEmail(), subject, mailMessage);
    }

    private StudentBean setupStudent() {
        StudentBean user = new StudentBean();
        Map<String, String[]> userFormValues = request.getParameterMap();

        String date_of_birth_fields = request.getParameter("dobY")
                + "-" + request.getParameter("dobM")
                + "-" + request.getParameter("dobD");
        String dateNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());

        BeanUtilsBean beanManager = BeanUtilsBean.getInstance();
        try {
            beanManager.populate(user, userFormValues);
            beanManager.setProperty(user, "dateOfBirth", date_of_birth_fields);
            beanManager.setProperty(user, "authenticated", false);
            beanManager.setProperty(user, "authString", UUID.randomUUID().toString());
            beanManager.setProperty(user, "dateRegistered", dateNow);
            beanManager.setProperty(user, "cookieToken", UUID.randomUUID().toString());
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.out.println("Error populating StudentBean");
            e.printStackTrace();
        }
        return user;
    }
}