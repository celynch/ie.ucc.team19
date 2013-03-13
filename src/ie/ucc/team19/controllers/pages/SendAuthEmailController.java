package ie.ucc.team19.controllers.pages;

import ie.ucc.team19.controllers.AbstractController;
import ie.ucc.team19.dao.*;
import ie.ucc.team19.service.FetchBean;
import ie.ucc.team19.service.SendEmail;

/**
 *
 * @author Cormac
 */
public class SendAuthEmailController extends AbstractController {

    public void execute() {
        this.setReturnPage("/index.jsp");
        this.getRequest().setAttribute("pageTitle", "TEST EMAIL SENT | UCC Summer Courses");
        CourseBean[] courses = new FetchBean().getCourseById("1");
        this.getRequest().setAttribute("courses", courses);
        LecturerBean[] lecturers = new FetchBean().getCourseLecturers("1");
        this.getRequest().setAttribute("lecturers", lecturers);
        VenueBean[] venues = new FetchBean().getCourseVenues("1");
        this.getRequest().setAttribute("venues", venues);
        
        new SendEmail().sendEmail( "101664280@gmail.com", "how now brown cow", "test email");
    }
}