package ie.ucc.team19.controllers.pages;

import ie.ucc.team19.controllers.AbstractController;
import ie.ucc.team19.dao.*;
import ie.ucc.team19.service.FetchBeanUtils;
import ie.ucc.team19.service.PropertiesReader;

/**
 * Controller for displaying a course page
 * @author Cormac
 */
public class ViewCourseController extends AbstractController {

    /**
     * Fetches the course details and lecturer and venue of the specified
     * course, for display in the view.
     */
    public void execute() {
        PropertiesReader properties = (PropertiesReader)
                request.getSession().getServletContext().getAttribute("properties");
        DBConnectionManager connector = new DBConnectionManager(properties);
        FetchBeanUtils fetcher = new FetchBeanUtils(connector);

        String courseId = request.getParameter("courseId");
        courseId = courseId==null ? "1" : courseId;
        CourseBean[] courses = fetcher.getCourseByCourseId(courseId);
        LecturerBean[] lecturers = fetcher.getLecturersByCourseId(courseId);
        VenueBean[] venues = fetcher.getVenuesByCourseId(courseId);
        String courseTitle = courses[0].getCourseTitle();

        request.setAttribute("courses", courses);
        request.setAttribute("lecturers", lecturers);
        request.setAttribute("venues", venues);

        setReturnPage("/viewCourse.jsp");
        request.setAttribute("pageTitle", courseTitle);
    }
}
