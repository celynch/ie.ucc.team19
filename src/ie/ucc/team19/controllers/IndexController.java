package ie.ucc.team19.controllers;

import ie.ucc.team19.dao.*;
import ie.ucc.team19.service.BeanFetcher;

//import ie.ucc.team19.daos.FooDao;
//import java.util.List;

/**
 *
 * @author Cormac
 */
public class IndexController extends AbstractController{

    public void execute() {
        this.setReturnPage("/index.jsp");
        this.getRequest().setAttribute("pageTitle", "Welcome | UCC Summer Courses");
        CourseBean[] courses = new BeanFetcher().getCourseDetails("1");
        this.getRequest().setAttribute("courses", courses);
        LecturerBean[] lecturers = new BeanFetcher().getCourseLecturers("1");
        this.getRequest().setAttribute("lecturers", lecturers);
        VenueBean[] venues = new BeanFetcher().getCourseVenues("1");
        this.getRequest().setAttribute("venues", venues);
    }
}
