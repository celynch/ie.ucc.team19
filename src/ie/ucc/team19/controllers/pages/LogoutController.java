package ie.ucc.team19.controllers.pages;

import ie.ucc.team19.controllers.AbstractController;
import ie.ucc.team19.dao.DBConnectionManager;
import ie.ucc.team19.dao.StudentBean;
import ie.ucc.team19.dao.UserBean;
import ie.ucc.team19.service.LoginUser;
import ie.ucc.team19.service.PropertiesReader;

/**
 * Controller for logout page.
 * @author Cormac
 */
public class LogoutController extends AbstractController{

    /**
     * Alters users cookie_token in database, so old cookies can't login.
     * Then logs user out by invalidating current session.
     */
    public void execute() {
        PropertiesReader properties = (PropertiesReader)
                request.getSession().getServletContext().getAttribute("properties");
        DBConnectionManager connector = new DBConnectionManager(properties);
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
