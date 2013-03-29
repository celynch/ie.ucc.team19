package ie.ucc.team19.controllers.pages;

import ie.ucc.team19.controllers.AbstractController;
import ie.ucc.team19.dao.*;
import ie.ucc.team19.service.FetchBeanUtils;
import ie.ucc.team19.service.PropertiesReader;

/**
 *
 * @author Cormac
 */
public class ViewCourseController extends AbstractController {

    public void execute() {
        PropertiesReader properties = (PropertiesReader)
                request.getSession().getServletContext().getAttribute("properties");
        DBConnectionManager connector = new DBConnectionManager(properties);
        FetchBeanUtils fetcher = new FetchBeanUtils(connector);

        String courseId = request.getParameter("courseId");
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
