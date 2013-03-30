package ie.ucc.team19.controllers.pages;

import ie.ucc.team19.controllers.AbstractController;
import ie.ucc.team19.dao.*;
import ie.ucc.team19.service.FetchBeanUtils;
import ie.ucc.team19.service.PropertiesReader;
import ie.ucc.team19.service.SendEmail;

/**
 * Test Class for dispatching email
 * @author Cormac
 */
public class SendAuthEmailController extends AbstractController {

    /**
     * Dispatches test email to specified address.
     */
    public void execute() {
        PropertiesReader properties = (PropertiesReader)
                request.getSession().getServletContext().getAttribute("properties");
        DBConnectionManager connector = new DBConnectionManager(properties);
        FetchBeanUtils fetcher = new FetchBeanUtils(connector);

        CourseBean[] courses = fetcher.getCourseByCourseId("1");
        LecturerBean[] lecturers = fetcher.getLecturersByCourseId("1");
        VenueBean[] venues = fetcher.getVenuesByCourseId("1");

        setReturnPage("/index.jsp");
        request.setAttribute("pageTitle", "TEST EMAIL SENT");
        request.setAttribute("courses", courses);
        request.setAttribute("lecturers", lecturers);
        request.setAttribute("venues", venues);

        new SendEmail(connector, properties).sendEmail(
                "101664280@gmail.com", "how now brown cow", "test email");
    }
}