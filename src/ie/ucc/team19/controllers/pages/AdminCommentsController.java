package ie.ucc.team19.controllers.pages;

import ie.ucc.team19.controllers.AbstractController;
import ie.ucc.team19.dao.CommentBean;
import ie.ucc.team19.dao.DBConnectionManager;
import ie.ucc.team19.dao.UserBean;
import ie.ucc.team19.service.FetchBeanUtils;
import ie.ucc.team19.service.PropertiesReader;
import ie.ucc.team19.service.SendEmail;
import ie.ucc.team19.service.UpdateUser;

/**
 * Controller class to handle request for the admin comments section.
 * @author Cormac
 */
public class AdminCommentsController extends AbstractController{

    /**
     * Fetches beans representing comments from the model, passed to the
     * view as request scoped objects. Also dispatches emails if page is
     * being reloaded after a response was submitted by the admin, the
     * message is sent and comment is marked as reviewed.
     */
    public void execute() {
        PropertiesReader properties = (PropertiesReader)
                request.getSession().getServletContext().getAttribute("properties");
        DBConnectionManager connector = new DBConnectionManager(properties);
        String returnPage = "/adminComments.jsp";
        String pageTitle = "Review Comments";

        UserBean user = (UserBean) request.getSession().getAttribute("user");
        if(user == null || !user.isAdmin()) {
            returnPage = "/admin.jsp";
            pageTitle = "Admin Access";
        }
        if(request.getParameter("respond") != null) {
            String commentId = request.getParameter("commentId");
            String email = request.getParameter("email");
            String subject = request.getParameter("subject");
            String mailMessage = request.getParameter("mailMessage");
            SendEmail sender = new SendEmail(connector);
            sender.sendEmail(email, subject, mailMessage);
            new UpdateUser(connector).updateComment(commentId);
        }

        CommentBean[] comments = new FetchBeanUtils(connector).getUnreviewedComments();
        request.setAttribute("comments", comments);
        setReturnPage(returnPage);
        request.setAttribute("pageTitle", pageTitle);
        request.setAttribute("admin", true);
    }
}
