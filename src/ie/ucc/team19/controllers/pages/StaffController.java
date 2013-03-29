package ie.ucc.team19.controllers.pages;

import ie.ucc.team19.controllers.AbstractController;
import ie.ucc.team19.dao.DBConnectionManager;
import ie.ucc.team19.dao.LecturerBean;
import ie.ucc.team19.service.FetchBeanUtils;
import ie.ucc.team19.service.PropertiesReader;

public class StaffController extends AbstractController {

    public void execute() {
        PropertiesReader properties = (PropertiesReader)
                request.getSession().getServletContext().getAttribute("properties");
        DBConnectionManager connector = new DBConnectionManager(properties);
        FetchBeanUtils fetcher = new FetchBeanUtils(connector);

        LecturerBean[] lecturers = fetcher.getLecturers();
        request.setAttribute("lecturers", lecturers);

        setReturnPage("/staff.jsp");
        request.setAttribute("pageTitle", "Summer Courses Staff");
    }
}
