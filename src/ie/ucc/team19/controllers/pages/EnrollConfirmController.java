package ie.ucc.team19.controllers.pages;

import ie.ucc.team19.controllers.AbstractController;
import ie.ucc.team19.dao.DBConnectionManager;
import ie.ucc.team19.service.EnrollStudent;

/**
 * Controller class to handle request to confirm course enrollment.
 * Accesses service to detect if conflict with user's current schedule
 * before allowing them to proceed.
 * @author Cormac
 *
 */
public class EnrollConfirmController extends AbstractController{

    /**
     * If user is not logged in, redirected to login challenge page.
     * If no courseId available (request manipulated via address bar etc),
     * redirected to front page. Otherwise, accesses model to determine
     * if conflict with current schedule, fetches data from model to
     * layout schedule display in view. Payment options displayed if no
     * conflict detected.
     */
    public void execute() {
        if(request.getSession().getAttribute("user") != null) { // is logged in
            DBConnectionManager connector = new DBConnectionManager();
            EnrollStudent enroller = new EnrollStudent(connector);
            if(request.getParameter("enrollCourseId") != null) {
                enroller.detectConflicts(request);
                setReturnPage("/enrollConfirm.jsp");
                request.setAttribute("pageTitle", "Course Enrollment");
            } else {
                setReturnPage("/");
                request.setAttribute("pageTitle", "Welcome");
            }
        } else { // not logged in, send login challenge page, will be referred back
            setReturnPage("/login.jsp");
            request.setAttribute("pageTitle", "Login Required");
        }
    }
}
