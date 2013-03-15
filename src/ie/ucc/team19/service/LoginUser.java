package ie.ucc.team19.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtilsBean;
import ie.ucc.team19.dao.DBConnectionManager;
import ie.ucc.team19.dao.StudentBean;

public class LoginUser {
    private StudentBean loginStudent(String email, String password_hash) {
        StudentBean student = new StudentBean();
        String query = "SELECT * FROM students WHERE email = '" + email + "'";
        ArrayList<Map<String, String[]>> studentDetails = new DBConnectionManager().Select(query);
        if(studentDetails.size() != 0) {    // email found
            if( studentDetails.get(0).get("passwordHash")[0].equals(password_hash)) { // password matches
                try {
                    BeanUtilsBean.getInstance().populate(student, studentDetails.get(0));
                } catch (IllegalAccessException | InvocationTargetException e) {
                    System.out.println("Error populating student bean");
                    e.printStackTrace();
                }
            }
        }
        return student;
    }

    public static StudentBean cookieValidate(String email, String cookie_token) {
        StudentBean student = new StudentBean();
        String query = "SELECT * FROM students WHERE email = '" + email + "'";
        ArrayList<Map<String, String[]>> studentDetails = new DBConnectionManager().Select(query);
        if(studentDetails.size() != 0) {    // email found
            if( studentDetails.get(0).get("cookieToken")[0].equals(cookie_token)) { // cookieToken matches
                try {
                    BeanUtilsBean.getInstance().populate(student, studentDetails.get(0));
                    cookie_token = UUID.randomUUID().toString();
                    BeanUtilsBean.getInstance().setProperty(student, "cookieToken", cookie_token);
                    new UpdateUser().updateCookieToken(email, cookie_token);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    System.out.println("Error populating student bean");
                    e.printStackTrace();
                }
            }
        }
        return student;
    }
    
    public void loginViaForm(HttpServletRequest request, HttpServletResponse response, StudentBean student) {
        student = loginStudent(request.getParameter("email"), request.getParameter("passwordHash"));
        if(student.isAuthenticated()) {
            request.getSession().setAttribute("user", student);
            setCookies(response, student, Boolean.parseBoolean(request.getParameter("rememberMe")), false);
        }
    }

    public void loginViaCookie(HttpServletRequest request, HttpServletResponse response, StudentBean student) {
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            String email = "";
            String cookie_token = "";
            for(Cookie cookie : cookies) {
                if(cookie.getName().equals("email")) {
                    email = cookie.getValue();
                } else if (cookie.getName().equals("cookieToken") ) {
                    cookie_token = cookie.getValue();
                }
            }
            student = LoginUser.cookieValidate(email, cookie_token);
            if(student.isAuthenticated()) {
                request.getSession().setAttribute("user", student);
                setCookies(response, student, Boolean.parseBoolean(request.getParameter("rememberMe")), false);
            }
        }
    }

    public void setCookies(HttpServletResponse response, StudentBean student, boolean rememberMe, boolean expire) {
        int SECONDS_PER_YEAR = 60*60*24*365;
        Cookie emailCookie = new Cookie("email", student.getEmail());
        Cookie cookieToken = new Cookie("cookieToken", student.getCookieToken());
        emailCookie.setDomain("localhost");
        cookieToken.setDomain("localhost");
        if(rememberMe) {
            emailCookie.setMaxAge(SECONDS_PER_YEAR);
            cookieToken.setMaxAge(SECONDS_PER_YEAR);
        }
        if(expire) {
            emailCookie.setMaxAge(0);
            cookieToken.setMaxAge(0);
        }
        response.addCookie(cookieToken);
        response.addCookie(emailCookie);
    }

    public void loginVerify(HttpServletRequest request, HttpServletResponse response, StudentBean student) {
        student = loginStudent(request.getParameter("email"), request.getParameter("passwordHash"));
        if(request.getParameter("authString").equals(student.getAuthString())) {
            System.out.println("good authString");
            student.setAuthenticated(true);
        } else {
            System.out.println("bad authString");
        }
        if(student.isAuthenticated()) {
            request.getSession().setAttribute("user", student);
            setCookies(response, student, Boolean.parseBoolean(request.getParameter("rememberMe")), false);
        }
        
    }
}
