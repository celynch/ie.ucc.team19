package ie.ucc.team19.controllers.pages;

import ie.ucc.team19.controllers.AbstractController;
import ie.ucc.team19.dao.CommentBean;
import ie.ucc.team19.dao.DBConnectionManager;
import ie.ucc.team19.service.FetchBeanUtils;
import ie.ucc.team19.service.SendEmail;
import ie.ucc.team19.service.UpdateUser;

public class AdminCommentsController extends AbstractController{

    /**
     *
     */
    public void execute() {
        DBConnectionManager connector = new DBConnectionManager();

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
        setReturnPage("/adminComments.jsp");
        getRequest().setAttribute("pageTitle", "Review Comments");
        getRequest().setAttribute("comments", comments);
    }
}
