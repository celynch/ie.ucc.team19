package ie.ucc.team19.controllers.pages;

import ie.ucc.team19.controllers.AbstractController;
import ie.ucc.team19.dao.CourseBean;
import ie.ucc.team19.dao.DBConnectionManager;
import ie.ucc.team19.dao.LecturerBean;
import ie.ucc.team19.dao.UserBean;
import ie.ucc.team19.dao.VenueBean;
import ie.ucc.team19.service.FetchBeanUtils;
import ie.ucc.team19.service.PropertiesReader;

/**
 *
 * @author Cormac
 */
public class AdminCourseEditController extends AbstractController{

    /**
     * Fetches beans from model for display in dashboard view.
     */
    public void execute() {
        PropertiesReader properties = (PropertiesReader)
                request.getSession().getServletContext().getAttribute("properties");
        DBConnectionManager connector = new DBConnectionManager(properties);
        FetchBeanUtils fetcher = new FetchBeanUtils(connector);

        String returnPage = "/adminCourseEdit.jsp";
        String pageTitle = "Course Edit";

        UserBean user = (UserBean) request.getSession().getAttribute("user");
        if(user == null || !user.isAdmin()) {
            returnPage = "/admin.jsp";
            pageTitle = "Admin Access";
        }

        CourseBean[] course = fetcher.getCourseByCourseId(request.getParameter("courseId"));
        LecturerBean[] lecs = fetcher.getLecturers();
        VenueBean[] vens = fetcher.getVenues();

        request.setAttribute("course", course[0]);
        request.setAttribute("venues", vens);
        request.setAttribute("lecturers", lecs);
        request.setAttribute("includeEditor", true);
        setReturnPage(returnPage);
        request.setAttribute("pageTitle", pageTitle);
        request.setAttribute("admin", true);
    }
}
