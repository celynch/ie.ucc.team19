package ie.ucc.team19.controllers.pages;

import ie.ucc.team19.controllers.AbstractController;
import ie.ucc.team19.dao.CommentBean;
import ie.ucc.team19.dao.DBConnectionManager;
import ie.ucc.team19.dao.UserBean;
import ie.ucc.team19.service.FetchBeanUtils;
import ie.ucc.team19.service.PropertiesReader;

/**
 * Controller class to handle request for the admin dashboard.
 * @author Cormac
 */
public class AdminDashBoardController extends AbstractController{

    /**
     * Fetches beans from model for display in dashboard view.
     */
    public void execute() {
        PropertiesReader properties = (PropertiesReader)
                request.getSession().getServletContext().getAttribute("properties");
        DBConnectionManager connector = new DBConnectionManager(properties);
        FetchBeanUtils fetcher = new FetchBeanUtils(connector);

        String returnPage = "/adminDashBoard.jsp";
        String pageTitle = "Administration DashBoard";

        UserBean user = (UserBean) request.getSession().getAttribute("user");
        if(user == null || !user.isAdmin()) {
            returnPage = "/admin.jsp";
            pageTitle = "Admin Access";
        }

        CommentBean[] comments = fetcher.getUnreviewedComments();
        request.setAttribute("comments", comments);
        String coursesCount = "SELECT COUNT(*) FROM courses";
        int numberOfCourses = Integer.valueOf(connector.Select(
                coursesCount, new Object[0]).get(0).get("COUNT(*)")[0]);
        request.setAttribute("numberOfCourses", numberOfCourses);
        String studentCount = "SELECT COUNT(*) FROM students";
        int numberOfStudents =  Integer.valueOf(connector.Select(
                studentCount, new Object[0]).get(0).get("COUNT(*)")[0]);
        request.setAttribute("numberOfStudents", numberOfStudents);
        String venueCount = "SELECT COUNT(*) FROM venues";
        int numberOfVenues =  Integer.valueOf(connector.Select(
                venueCount, new Object[0]).get(0).get("COUNT(*)")[0]);
        request.setAttribute("numberOfVenues", numberOfVenues);
        String lecturerCount = "SELECT COUNT(*) FROM venues";
        int numberOfLecturers =  Integer.valueOf(connector.Select(
                lecturerCount, new Object[0]).get(0).get("COUNT(*)")[0]);
        request.setAttribute("numberOfLecturers", numberOfLecturers);

        setReturnPage(returnPage);
        request.setAttribute("pageTitle", pageTitle);
        request.setAttribute("admin", true);
    }
}
