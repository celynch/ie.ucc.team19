package ie.ucc.team19.controllers.pages;

import ie.ucc.team19.controllers.AbstractController;
import ie.ucc.team19.dao.*;
import ie.ucc.team19.service.FetchBean;

/**
 *
 * @author Cormac
 */
public class ViewCourseController extends AbstractController {

    public void execute() {
        setReturnPage("/viewCourse.jsp");
        String courseId = getRequest().getParameter("courseId");
        
        CourseBean[] courses = new FetchBean().getCourseByCourseId(courseId);
        getRequest().setAttribute("courses", courses);
        LecturerBean[] lecturers = new FetchBean().getLecturersByCourseId(courseId);
        getRequest().setAttribute("lecturers", lecturers);
        VenueBean[] venues = new FetchBean().getVenuesByCourseId(courseId);
        getRequest().setAttribute("venues", venues);
        
        String courseTitle = courses[0].getCourseTitle();
        getRequest().setAttribute("pageTitle", courseTitle + " | UCC Summer Courses");
    }
}
