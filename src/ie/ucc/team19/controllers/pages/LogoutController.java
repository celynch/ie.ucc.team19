package ie.ucc.team19.controllers.pages;

import javax.servlet.http.HttpServletResponse;

import ie.ucc.team19.controllers.AbstractController;
import ie.ucc.team19.dao.AdminBean;
import ie.ucc.team19.dao.StudentBean;
import ie.ucc.team19.dao.UserBean;
import ie.ucc.team19.service.LoginUser;

/**
 *
 * @author Cormac
 */
public class LogoutController extends AbstractController{

    public void execute() {
        HttpServletResponse response = getResponse();
        UserBean user = (UserBean) request.getSession().getAttribute("user");
        if(!(user instanceof AdminBean)) {
            StudentBean student = (StudentBean) request.getSession().getAttribute("user");
            new LoginUser().setCookies(response, student, request.getServerName(), true, true);
        }
        request.getSession().invalidate();
        setReturnPage("/logout.jsp");
        request.setAttribute("pageTitle", "Logged Out");
    }
}
