package ie.ucc.team19.controllers.pages;

import ie.ucc.team19.controllers.AbstractController;
import ie.ucc.team19.dao.*;
import ie.ucc.team19.service.FetchBean;

/**
 *
 * @author Cormac
 */
public class IndexController extends AbstractController {

    public void execute() {
        this.setReturnPage("/index.jsp");
        this.getRequest().setAttribute("pageTitle", "Welcome | UCC Summer Courses");
        CourseBean[] courses = new FetchBean().getCourseById("1");
        this.getRequest().setAttribute("courses", courses);
        LecturerBean[] lecturers = new FetchBean().getCourseLecturers("1");
        this.getRequest().setAttribute("lecturers", lecturers);
        VenueBean[] venues = new FetchBean().getCourseVenues("1");
        this.getRequest().setAttribute("venues", venues);
    }
}
