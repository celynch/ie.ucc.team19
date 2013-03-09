package ie.ucc.team19.controllers;

import ie.ucc.team19.controllers.Controller;
import ie.ucc.team19.controllers.ControllerFactory;
import ie.ucc.team19.dao.StudentBean;
import ie.ucc.team19.service.LoginUser;
import ie.ucc.team19.service.UpdateUser;

import java.io.IOException;
import java.util.UUID;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void init() throws ServletException {
        super.init();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        StudentBean student;
        response.setContentType("text/html;charset=UTF-8");
        if(request.getSession().getAttribute("user") == null) {
            if(request.getParameter("login") != null && request.getParameter("login").equals("login")) {
                student = LoginUser.loginStudent(request.getParameter("email"), request.getParameter("password_hash"));
                if(student.isAuthenticated()) {
                    request.getSession().setAttribute("user", student);
                }
            } else {
                Cookie[] cookies = request.getCookies();
                if(cookies != null) {
                    String email = "";
                    String cookie_token = "";
                    for(Cookie cookie : cookies) {
                        if(cookie.getName().equals("email")) {
                            email = cookie.getValue();
                        } else if (cookie.getName().equals("cookie_token") ) {
                            cookie_token = cookie.getValue();
                        }
                    }
                    student = LoginUser.cookieValidate(email, cookie_token);
                    if(student.isAuthenticated()) {
                        request.getSession().setAttribute("user", student);
                        String cookieTokenUpdate = UUID.randomUUID().toString();
                        response.addCookie(new Cookie(student.getEmail(), cookieTokenUpdate));
                        student.setCookie_token(cookieTokenUpdate);
                        UpdateUser.updateCookieToken(email, cookieTokenUpdate);
                    }
                }
            }
        }

        try {
            String controller = getURLPattern(request);
            //Instantiate controller class
            Controller control = ControllerFactory.getControllerByFullClassName(controller);
            control.init(request);
            control.execute();
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(control.getReturnPage());
            requestDispatcher.forward(request, response);
        } catch (Exception e) {
            System.out.println("Error determining request controller.");
            e.printStackTrace();
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    private String getURLPattern(HttpServletRequest request) {
        //get the request's url
        String url = request.getRequestURL().substring(request.getRequestURL().lastIndexOf("/") + 1, request.getRequestURL().length());
        System.out.println(url);
        //get Controllers name from request
        String controller = url.substring(0, url.indexOf(".") != -1 ? url.indexOf(".") : url.length());
        controller = controller.length() == 0 ? "Index" : controller;
        controller = controller.substring(0,1).toUpperCase() + controller.substring(1);
        return controller;
    }
}
