package ie.ucc.team19.controllers.pages;

import ie.ucc.team19.controllers.AbstractController;
import ie.ucc.team19.dao.DBConnectionManager;
import ie.ucc.team19.service.EnrollStudent;

public class EnrollConfirmController extends AbstractController{

    /**
     *
     */
    public void execute() {
        if(request.getSession().getAttribute("user") != null) { // is logged in
            DBConnectionManager connector = new DBConnectionManager();
            boolean conflictDetected = EnrollStudent.detectConflicts(request, connector);
            request.setAttribute("conflictDetected", conflictDetected);

            setReturnPage("/enrollConfirm.jsp");
            request.setAttribute("pageTitle", "Course Enrollment");
        } else { // not logged in, send login challenge page, will be referred back
            setReturnPage("/login.jsp");
            request.setAttribute("pageTitle", "Login Required");
        }
    }
}
