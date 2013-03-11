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
        HttpServletResponse response = this.getResponse();
        StudentBean student = (StudentBean) this.getRequest().getSession().getAttribute("user");
        new LoginUser().setCookies(response, student, false, true);
        this.getRequest().getSession().invalidate();
        this.setReturnPage("/logout.jsp");
        this.getRequest().setAttribute("pageTitle", "Logged Out | UCC Summer Courses");
    }

}
