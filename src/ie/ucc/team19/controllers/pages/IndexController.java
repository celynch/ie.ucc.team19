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
        setReturnPage("/index.jsp");
        getRequest().setAttribute("pageTitle", "Welcome");
        CourseBean[] courses = new FetchBean().getCourseByCourseId("1");
        getRequest().setAttribute("courses", courses);
        LecturerBean[] lecturers = new FetchBean().getLecturersByCourseId("1");
        getRequest().setAttribute("lecturers", lecturers);
        VenueBean[] venues = new FetchBean().getVenuesByCourseId("1");
        getRequest().setAttribute("venues", venues);
    }
}
