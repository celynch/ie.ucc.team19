package ie.ucc.team19.controllers.pages;

import ie.ucc.team19.controllers.AbstractController;
import ie.ucc.team19.dao.*;
import ie.ucc.team19.service.*;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.beanutils.BeanUtilsBean;

public class RegisterCompleteController extends AbstractController{
    public void execute() {
        StudentBean user = setupStudent();
        InsertUser.createStudent(user);
        this.setReturnPage("/registerComplete.jsp");
        this.getRequest().setAttribute("pageTitle", "Registration Completed | UCC Summer Courses");
        String subject = "UCC Summer Courses | Welcome";
        String mailMessage = "Thanks for joining UCC Summer Courses.\n "
                + "We listed your sign in details below, make sure you keep them safe. "
                + "To verify your email address, please follow this link:\n"
                + "<a href=\"http://localhost:8080/team19/pages/login?authString="
                + user.getAuthString() + "\">Complete Registration</a>\n"
                + "Your email address: " + user.getEmail()
                + "\n<blockquote>\"Where Finbarr taught, let Munster learn\"</blockquote>\n- The UCC Summer Courses Team ";
        new SendEmail().sendEmail( user.getEmail(), subject, mailMessage);
    }
    
    private StudentBean setupStudent() {
        StudentBean user = new StudentBean();
        HttpServletRequest request = this.getRequest();
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