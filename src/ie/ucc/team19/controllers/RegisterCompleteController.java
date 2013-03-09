package ie.ucc.team19.controllers;

import ie.ucc.team19.dao.*;
import ie.ucc.team19.service.*;

import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.beanutils.BeanUtilsBean;

public class RegisterCompleteController extends AbstractController{
    public void execute() {
        StudentBean user = new StudentBean();
        HttpServletRequest request = this.getRequest();
        request.getSession().setAttribute("user", user);
        Map<String, String[]> userFormValues = request.getParameterMap();
        String date_of_birth_fields = request.getParameter("dobY") + "-" + request.getParameter("dobM") + "-" + request.getParameter("dobD");
        Date date_of_birth = Date.valueOf(date_of_birth_fields);
        BeanUtilsBean beanManager = new BeanUtilsBean();
        try {
            beanManager.populate(user, userFormValues);
            beanManager.setProperty(user, "date_of_birth", date_of_birth);
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.out.println("Error populating StudentBean");
            e.printStackTrace();
        }

        InsertUser.createStudent(user);
        this.setReturnPage("/registerComplete.jsp");
        this.getRequest().setAttribute("pageTitle", "Registration Completed | UCC Summer Courses");
    }
}