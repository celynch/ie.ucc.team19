package ie.ucc.team19.controllers.pages;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import ie.ucc.team19.controllers.AbstractController;
import ie.ucc.team19.dao.UserBean;

public class AdminController extends AbstractController{

    /**
     * Fetches beans from model for display in dashboard view.
     */
    public void execute() {
        UserBean user = (UserBean) request.getSession().getAttribute("user");
        if(user!=null && !user.isAdmin()) {
            LogoutController control = new LogoutController();
            control.init(request, response);
            control.execute();
            response.setContentType("text/html;charset=UTF-8");
            RequestDispatcher requestDispatcher
                    = request.getServletContext().getRequestDispatcher(control.getReturnPage());
            try {
                requestDispatcher.forward(request, response);
            } catch (ServletException | IOException e) {
                System.out.println("Error forwarding page");
                e.printStackTrace();
            }
        } else {
            setReturnPage("/admin.jsp");
            request.setAttribute("pageTitle", "Admin Access");
            request.setAttribute("admin", true);
        }
    }
}
