package ie.ucc.team19.controllers.pages;

import ie.ucc.team19.controllers.AbstractController;
import ie.ucc.team19.dao.DBConnectionManager;
import ie.ucc.team19.dao.LecturerBean;
import ie.ucc.team19.service.FetchBeanUtils;

public class StaffController extends AbstractController {

    public void execute() {
        DBConnectionManager connector = new DBConnectionManager();
        FetchBeanUtils fetcher = new FetchBeanUtils(connector);

        LecturerBean[] lecturers = fetcher.getLecturers();
        setReturnPage("/staff.jsp");
        request.setAttribute("pageTitle", "Summer Courses Staff");
        request.setAttribute("lecturers", lecturers);
    }
}
