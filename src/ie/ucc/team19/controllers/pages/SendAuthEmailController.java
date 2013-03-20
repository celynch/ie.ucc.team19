package ie.ucc.team19.controllers.pages;

import ie.ucc.team19.controllers.AbstractController;
import ie.ucc.team19.dao.*;
import ie.ucc.team19.service.FetchBeanUtils;
import ie.ucc.team19.service.SendEmail;

/**
 *
 * @author Cormac
 */
public class SendAuthEmailController extends AbstractController {

    public void execute() {
        DBConnectionManager connector = new DBConnectionManager();
        FetchBeanUtils fetcher = new FetchBeanUtils(connector);

        CourseBean[] courses = fetcher.getCourseByCourseId("1");
        LecturerBean[] lecturers = fetcher.getLecturersByCourseId("1");
        VenueBean[] venues = fetcher.getVenuesByCourseId("1");

        setReturnPage("/index.jsp");
        getRequest().setAttribute("pageTitle", "TEST EMAIL SENT");
        getRequest().setAttribute("courses", courses);
        getRequest().setAttribute("lecturers", lecturers);
        getRequest().setAttribute("venues", venues);

        new SendEmail().sendEmail( "101664280@gmail.com", "how now brown cow", "test email");
    }
}