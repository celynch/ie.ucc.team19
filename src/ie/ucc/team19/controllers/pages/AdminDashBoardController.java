package ie.ucc.team19.controllers.pages;

import ie.ucc.team19.controllers.AbstractController;
import ie.ucc.team19.dao.CommentBean;
import ie.ucc.team19.dao.DBConnectionManager;
import ie.ucc.team19.service.FetchBeanUtils;

/**
 * Controller class to handle request for the admin dashboard.
 * @author Cormac
 */
public class AdminDashBoardController extends AbstractController{

    /**
     * Fetches beans from model for display in dashboard view.
     */
    public void execute() {
        DBConnectionManager connector = new DBConnectionManager();
        FetchBeanUtils fetcher = new FetchBeanUtils(connector);

        setReturnPage("/adminDashBoard.jsp");
        request.setAttribute("pageTitle", "Administration DashBoard");
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
    }
}
