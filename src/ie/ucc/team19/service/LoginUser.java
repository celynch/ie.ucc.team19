package ie.ucc.team19.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtilsBean;

import ie.ucc.team19.dao.AdminBean;
import ie.ucc.team19.dao.DBConnectionManager;
import ie.ucc.team19.dao.StudentBean;

public class LoginUser {
    private StudentBean loginStudent(String email, String passwordHash) {
        StudentBean student = new StudentBean();
        String query = "SELECT * FROM students WHERE email = ?";
        Object[] params = {email};
        ArrayList<Map<String, String[]>> studentDetails = new DBConnectionManager().Select(query, params);
        if(studentDetails.size() != 0) {// user exists
            if( studentDetails.get(0).get("passwordHash")[0].equals(passwordHash)) { // password matches
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

    private AdminBean loginAdmin(String adminName, String adminPassword) {
        AdminBean admin = new AdminBean();
        String query = "SELECT * FROM admins WHERE adminName = ?";
        Object[] params = {adminName};
        ArrayList<Map<String, String[]>> adminDetails = new DBConnectionManager().Select(query, params);
        if(adminDetails.size() != 0) {// user exists
            if( adminDetails.get(0).get("adminPassword")[0].equals(adminPassword)) { // password matches
                try {
                    BeanUtilsBean.getInstance().populate(admin, adminDetails.get(0));
                } catch (IllegalAccessException | InvocationTargetException e) {
                    System.out.println("Error populating admin bean");
                    e.printStackTrace();
                }
            }
        }
        return admin;
    }

    public static StudentBean cookieValidate(String email, String cookie_token) {
        StudentBean student = new StudentBean();
        String query = "SELECT * FROM students WHERE email = ?";
        Object[] params = {email};
        ArrayList<Map<String, String[]>> studentDetails = new DBConnectionManager().Select(query, params);
        if(studentDetails.size() != 0) {    // email found
            if( studentDetails.get(0).get("cookieToken")[0].equals(cookie_token)) { // cookieToken matches
                try {
                    BeanUtilsBean.getInstance().populate(student, studentDetails.get(0));
                    cookie_token = UUID.randomUUID().toString();
                    BeanUtilsBean.getInstance().setProperty(student, "cookieToken", cookie_token);
                    UpdateUser updater = new UpdateUser(new DBConnectionManager());
                    updater.updateCookieToken(email, cookie_token);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    System.out.println("Error populating student bean");
                    e.printStackTrace();
                }
            }
        }
        return student;
    }

    public boolean loginViaForm(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String passwordHash = request.getParameter("passwordHash");
        StudentBean student = loginStudent(email, passwordHash);
        boolean result = false;
        if(student.getEmail() != null && student.isAuthenticated()) {
            result = true;
            request.getSession().setAttribute("user", student);
            setCookies(response, student, request.getServerName(),
                    Boolean.parseBoolean(request.getParameter("rememberMe")), false);
        }
        return result;
    }

    public void loginViaCookie(HttpServletRequest request, HttpServletResponse response) {
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
            StudentBean student = LoginUser.cookieValidate(email, cookie_token);
            if(student.getEmail() != null && student.isAuthenticated()) {
                request.getSession().setAttribute("user", student);
                setCookies(response, student, request.getServerName(),
                        Boolean.parseBoolean(request.getParameter("rememberMe")), false);
            }
        }
    }

    public void setCookies(HttpServletResponse response, StudentBean student, String host, boolean rememberMe, boolean expire) {
        int SECONDS_PER_YEAR = 60*60*24*365;
        Cookie emailCookie = new Cookie("email", student.getEmail());
        Cookie cookieToken = new Cookie("cookieToken", student.getCookieToken());
        emailCookie.setDomain(host);
        cookieToken.setDomain(host);
        if(rememberMe) {
            emailCookie.setMaxAge(expire ? 0 : SECONDS_PER_YEAR);
            cookieToken.setMaxAge(expire ? 0 : SECONDS_PER_YEAR);
        }
        response.addCookie(cookieToken);
        response.addCookie(emailCookie);
    }

    public void loginVerify(HttpServletRequest request, HttpServletResponse response) {
        StudentBean student = loginStudent(request.getParameter("email"), request.getParameter("passwordHash"));
        if(request.getParameter("authString").equals(student.getAuthString())) {
            student.setAuthenticated(true);
            request.getSession().setAttribute("user", student);
            setCookies(response, student, request.getServerName(),
                    Boolean.parseBoolean(request.getParameter("rememberMe")), false);
        }
        
    }

    public void loginAdminForm(HttpServletRequest request,
            HttpServletResponse response) {
        String adminName = request.getParameter("adminName");
        String adminPassword = request.getParameter("adminPassword");
        AdminBean admin = loginAdmin(adminName, adminPassword);
        request.getSession().setAttribute("user", admin);
    }
}
