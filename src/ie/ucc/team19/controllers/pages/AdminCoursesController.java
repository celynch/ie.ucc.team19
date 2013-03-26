package ie.ucc.team19.controllers.pages;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;
import ie.ucc.team19.controllers.AbstractController;
import ie.ucc.team19.dao.CourseBean;
import ie.ucc.team19.dao.DBConnectionManager;
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

        if(request.getParameter("addCourse") != null) {
            CourseBean course = setupCourse();
            new InsertData(connector).createCourse(course);
        }
        
        request.setAttribute("includeEditor", true);
        setReturnPage("/adminCourses.jsp");
        request.setAttribute("pageTitle", "Courses Management");
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
