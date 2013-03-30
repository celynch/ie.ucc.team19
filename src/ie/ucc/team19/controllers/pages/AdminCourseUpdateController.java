package ie.ucc.team19.controllers.pages;

import ie.ucc.team19.controllers.AbstractController;
import ie.ucc.team19.dao.CourseBean;
import ie.ucc.team19.dao.DBConnectionManager;
import ie.ucc.team19.dao.UserBean;
import ie.ucc.team19.service.FetchBeanUtils;
import ie.ucc.team19.service.FormValidater;
import ie.ucc.team19.service.InsertData;
import ie.ucc.team19.service.PropertiesReader;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtilsBean;

public class AdminCourseUpdateController extends AbstractController{

    /**
     * Fetches beans from model for display in dashboard view.
     */
    public void execute() {
        PropertiesReader properties = (PropertiesReader)
                request.getSession().getServletContext().getAttribute("properties");
        DBConnectionManager connector = new DBConnectionManager(properties);
        FetchBeanUtils fetcher = new FetchBeanUtils(connector);
        InsertData inserter = new InsertData(connector);
        String returnPage = "/adminCourses.jsp";
        String pageTitle = "Courses Management";

        UserBean user = (UserBean) request.getSession().getAttribute("user");
        if(user == null || !user.isAdmin()) {
            returnPage = "/admin.jsp";
            pageTitle = "Admin Access";
        }

        if(request.getParameter("updateCourse") != null) {
            String error = new FormValidater().checkForm(request.getParameterMap());
            if(error == null) {
                int courseId = Integer.valueOf(request.getParameter("courseId"));
                int lecturerId = Integer.valueOf(request.getParameter("lecturerId"));
                int venueId = Integer.valueOf(request.getParameter("venueId"));
                CourseBean course = setupCourse(fetcher, courseId);
                if(lecturerId>0){inserter.setLecturer(courseId, lecturerId);}
                if(venueId>0){inserter.setVenue(courseId, venueId);}
                inserter.updateCourse(course);
                request.setAttribute("courseUpdated", "Updates to " + course.getCourseTitle() + " completed.");
            } else {
                request.setAttribute("updateError", error);
            }
        }
        request.setAttribute("courses", fetcher.getCourses());
        request.setAttribute("venues", fetcher.getVenues());
        request.setAttribute("lecturers", fetcher.getLecturers());
        request.setAttribute("includeEditor", true);
        setReturnPage(returnPage);
        request.setAttribute("pageTitle", pageTitle);
        request.setAttribute("admin", true);
    }

    /**
     * 
     * @return
     */
    private CourseBean setupCourse(FetchBeanUtils fetcher, Integer courseId) {
        CourseBean[] course = fetcher.getCourseByCourseId(courseId.toString());
        Map<String, String[]> userFormValues = request.getParameterMap();
        BeanUtilsBean beanManager = BeanUtilsBean.getInstance();
        try {
            beanManager.populate(course[0], userFormValues);
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.out.println("Error updating CourseBean");
            e.printStackTrace();
        }
        return course[0];
    }
}
