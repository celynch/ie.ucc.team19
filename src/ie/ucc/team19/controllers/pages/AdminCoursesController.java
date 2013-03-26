package ie.ucc.team19.controllers.pages;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtilsBean;
import ie.ucc.team19.controllers.AbstractController;
import ie.ucc.team19.dao.CourseBean;
import ie.ucc.team19.dao.DBConnectionManager;
import ie.ucc.team19.service.FetchBeanUtils;
import ie.ucc.team19.service.InsertData;

/**
 * Controller class to handle request for the admin dashboard.
 * @author Cormac
 */
public class AdminCoursesController extends AbstractController{

    /**
     * Fetches beans from model for display in dashboard view.
     */
    public void execute() {
        DBConnectionManager connector = new DBConnectionManager();
        FetchBeanUtils fetcher = new FetchBeanUtils(connector);
        InsertData inserter = new InsertData(connector);

        if(request.getParameter("addCourse") != null) {
            CourseBean course = setupCourse();
            int lecturerId = Integer.valueOf(request.getParameter("lecturerId"));
            int venueId = Integer.valueOf(request.getParameter("venueId"));
            inserter.createCourse(course);
            if(lecturerId != -1) {inserter.setLecturer(course.getCourseId(), lecturerId);}
            if(venueId != -1) {inserter.setVenue(course.getCourseId(), venueId);}
        }
        request.setAttribute("courses", fetcher.getCourses());
        request.setAttribute("venues", fetcher.getVenues());
        request.setAttribute("lecturers", fetcher.getLecturers());
        request.setAttribute("includeEditor", true);
        setReturnPage("/adminCourses.jsp");
        request.setAttribute("pageTitle", "Courses Management");
        request.setAttribute("admin", true);
    }

    /**
     * 
     * @return
     */
    private CourseBean setupCourse() {
        CourseBean course = new CourseBean();
        Map<String, String[]> userFormValues = request.getParameterMap();
        BeanUtilsBean beanManager = BeanUtilsBean.getInstance();
        try {
            beanManager.populate(course, userFormValues);
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.out.println("Error populating VenueBean");
            e.printStackTrace();
        }
        return course;
    }
}
