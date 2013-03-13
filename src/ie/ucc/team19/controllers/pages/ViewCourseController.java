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
        this.setReturnPage("/viewCourse.jsp");
        String courseId = this.getRequest().getParameter("courseId");
        
        CourseBean[] courses = new FetchBean().getCourseById(courseId);
        this.getRequest().setAttribute("courses", courses);
        LecturerBean[] lecturers = new FetchBean().getCourseLecturers(courseId);
        this.getRequest().setAttribute("lecturers", lecturers);
        VenueBean[] venues = new FetchBean().getCourseVenues(courseId);
        this.getRequest().setAttribute("venues", venues);
        
        String courseTitle = courses[0].getCourseTitle();
        this.getRequest().setAttribute("pageTitle", courseTitle + " | UCC Summer Courses");
    }
}
