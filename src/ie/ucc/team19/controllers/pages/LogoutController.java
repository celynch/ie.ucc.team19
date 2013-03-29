package ie.ucc.team19.controllers.pages;

import javax.servlet.http.HttpServletResponse;

import ie.ucc.team19.controllers.AbstractController;
import ie.ucc.team19.dao.DBConnectionManager;
import ie.ucc.team19.dao.StudentBean;
import ie.ucc.team19.dao.UserBean;
import ie.ucc.team19.service.LoginUser;
import ie.ucc.team19.service.PropertiesReader;

/**
 *
 * @author Cormac
 */
public class LogoutController extends AbstractController{

    public void execute() {
        PropertiesReader properties = (PropertiesReader)
                request.getSession().getServletContext().getAttribute("properties");
        DBConnectionManager connector = new DBConnectionManager(properties);
        HttpServletResponse response = getResponse();
        UserBean user = (UserBean) request.getSession().getAttribute("user");
        if(user != null && !user.isAdmin()) {
            StudentBean student = (StudentBean) request.getSession().getAttribute("user");
            new LoginUser(connector).setCookies(response, student, request.getServerName(), true, true);
        }
        request.getSession().invalidate();
        setReturnPage("/logout.jsp");
        request.setAttribute("pageTitle", "Logged Out");
    }
}
