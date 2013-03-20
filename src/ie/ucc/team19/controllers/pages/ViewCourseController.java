package ie.ucc.team19.controllers.pages;

import ie.ucc.team19.controllers.AbstractController;
import ie.ucc.team19.dao.*;
import ie.ucc.team19.service.FetchBeanUtils;

/**
 *
 * @author Cormac
 */
public class ViewCourseController extends AbstractController {

    public void execute() {
        DBConnectionManager connector = new DBConnectionManager();
        FetchBeanUtils fetcher = new FetchBeanUtils(connector);
        String courseId = getRequest().getParameter("courseId");

        CourseBean[] courses = fetcher.getCourseByCourseId(courseId);
        LecturerBean[] lecturers = fetcher.getLecturersByCourseId(courseId);
        VenueBean[] venues = fetcher.getVenuesByCourseId(courseId);
        String courseTitle = courses[0].getCourseTitle();

        setReturnPage("/viewCourse.jsp");
        getRequest().setAttribute("courses", courses);
        getRequest().setAttribute("lecturers", lecturers);
        getRequest().setAttribute("venues", venues);
        getRequest().setAttribute("pageTitle", courseTitle);
    }
}
