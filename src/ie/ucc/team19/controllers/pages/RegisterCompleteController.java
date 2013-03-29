package ie.ucc.team19.controllers.pages;

import ie.ucc.team19.controllers.AbstractController;
import ie.ucc.team19.dao.*;
import ie.ucc.team19.service.*;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.UUID;
import org.apache.commons.beanutils.BeanUtilsBean;

public class RegisterCompleteController extends AbstractController{
    public void execute() {
        String error = new FormValidater().checkForm(request.getParameterMap());
        if(error == null) {
            PropertiesReader properties = (PropertiesReader)
                    request.getSession().getServletContext().getAttribute("properties");
            DBConnectionManager connector = new DBConnectionManager(properties);
            StudentBean user = setupStudent();
            new InsertData(connector).createStudent(user);
            sendVerifyEmail(user, connector);

            setReturnPage("/registerComplete.jsp");
            request.setAttribute("pageTitle", "Registration Completed");
        } else {
            setReturnPage("/register.jsp");
            request.setAttribute("pageTitle", "Register");
            request.setAttribute("year", Calendar.getInstance().get(Calendar.YEAR));
            request.setAttribute("registerError", error);
            request.setAttribute("retry", request.getParameterMap());
        }
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
    
    private void sendVerifyEmail(StudentBean user, DBConnectionManager connector) {
        String subject = "UCC Summer Courses | Welcome";
        StringBuilder mailMessage = new StringBuilder();
        String serverName = (String) request.getAttribute("serverName");
        mailMessage.append("<div><p>Thanks for joining UCC Summer Courses.</p>");
        mailMessage.append("<p>We listed your sign in details below, make sure you keep them safe.</p>");
        mailMessage.append("<p>To verify your email address, please follow this link: ");
        mailMessage.append("<a href=\"http://" + serverName +  "/team19/pages/login");
        mailMessage.append("?authString=" + user.getAuthString());
        mailMessage.append("&email=" + user.getEmail() +"\">Complete Registration</a></p>");
        mailMessage.append("<p>Your email address: " + user.getEmail() + "</p>");
        mailMessage.append("<blockquote>\"Where Finbarr taught, let Munster learn\"</blockquote>");
        mailMessage.append("<p>- The UCC Summer Courses Team</p></div>");
        new SendEmail(connector).sendEmail( user.getEmail(), subject, mailMessage.toString());
    }
}
