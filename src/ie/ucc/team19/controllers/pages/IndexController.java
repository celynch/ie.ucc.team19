package ie.ucc.team19.controllers.pages;

import ie.ucc.team19.controllers.AbstractController;
import ie.ucc.team19.dao.*;
import ie.ucc.team19.service.FetchBeanUtils;

/**
 *
 * @author Cormac
 */
public class IndexController extends AbstractController {

    public void execute() {
        DBConnectionManager connector = new DBConnectionManager();
        FetchBeanUtils fetcher = new FetchBeanUtils(connector);

        CourseBean[] courses = fetcher.getCourseByCourseId("1");
        LecturerBean[] lecturers = fetcher.getLecturersByCourseId("1");
        VenueBean[] venues = fetcher.getVenuesByCourseId("1");

        setReturnPage("/index.jsp");
        getRequest().setAttribute("pageTitle", "Welcome");
        getRequest().setAttribute("courses", courses);
        getRequest().setAttribute("lecturers", lecturers);
        getRequest().setAttribute("venues", venues);
    }
}
