package ie.ucc.team19.controllers.pages;

import javax.servlet.http.HttpServletResponse;

import ie.ucc.team19.controllers.AbstractController;
import ie.ucc.team19.dao.StudentBean;
import ie.ucc.team19.service.LoginUser;

/**
 *
 * @author Cormac
 */
public class LogoutController extends AbstractController{

    public void execute() {
        HttpServletResponse response = getResponse();
        StudentBean student = (StudentBean) getRequest().getSession().getAttribute("user");
        new LoginUser().setCookies(response, student, getRequest().getServerName(), true, true);
        getRequest().getSession().invalidate();
        setReturnPage("/logout.jsp");
        getRequest().setAttribute("pageTitle", "Logged Out");
    }
}
