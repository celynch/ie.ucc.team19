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
        setReturnPage("/index.jsp");
        getRequest().setAttribute("pageTitle", "TEST EMAIL SENT");
        CourseBean[] courses = new FetchBean().getCourseByCourseId("1");
        getRequest().setAttribute("courses", courses);
        LecturerBean[] lecturers = new FetchBean().getLecturersByCourseId("1");
        getRequest().setAttribute("lecturers", lecturers);
        VenueBean[] venues = new FetchBean().getVenuesByCourseId("1");
        getRequest().setAttribute("venues", venues);
        
        new SendEmail().sendEmail( "101664280@gmail.com", "how now brown cow", "test email");
    }
}