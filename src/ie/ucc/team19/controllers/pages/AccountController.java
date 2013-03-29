package ie.ucc.team19.controllers.pages;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtilsBean;
import ie.ucc.team19.controllers.AbstractController;
import ie.ucc.team19.dao.CourseBean;
import ie.ucc.team19.dao.DBConnectionManager;
import ie.ucc.team19.dao.StudentBean;
import ie.ucc.team19.dao.UserBean;
import ie.ucc.team19.service.EnrollStudent;
import ie.ucc.team19.service.FormValidater;
import ie.ucc.team19.service.PropertiesReader;
import ie.ucc.team19.service.UpdateUser;

/**
 * Controller class to handle request for the user accounts page.
 * @author Cormac
 */
public class AccountController  extends AbstractController {

    /**
     * Sets return page and title, request scoped. Makes page not
     * cacheable by browser or proxy. Prevents a logged out user from
     * viewing this secure page using browser history or back button.
     */
    public void execute() {
        UserBean user = (StudentBean) request.getSession().getAttribute("user");
        String returnPage = "/account.jsp";
        String pageTitle = "Your Account";
        if(user==null || user.isAdmin()) {
            returnPage = "/index.jsp";
            pageTitle = "Welcome";
        } else {
            StudentBean student = (StudentBean)user;
            PropertiesReader properties = (PropertiesReader)
                    request.getSession().getServletContext().getAttribute("properties");
            DBConnectionManager connector = new DBConnectionManager(properties);
            EnrollStudent enroller = new EnrollStudent(connector);
        
            CourseBean[] courses = enroller.getCoursesByStudentId(student.getStudentId());
            request.setAttribute("courses", courses);

            String error = null;
            if(request.getParameter("editStudent")!=null) {
                error = new FormValidater().checkForm(request.getParameterMap());
                if(error==null) {
                    student = editStudent(student);
                    new UpdateUser(connector).updateStudent(student);
                    request.setAttribute("edited", true);
                } else {
                    request.setAttribute("editError", error);
                }
            }
            if(request.getParameter("unenroll")!=null) {
                String courseId = request.getParameter("courseId");
                System.out.println("unenrolling from course : " + courseId);
                enroller.unEnrollFromCourse(courseId, student.getStudentId());
            }
        }

        //Solutions for JSP pages and Struts, By Kevin H. Le, JavaWorld.com, 09/27/04
        response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
        response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
        response.setDateHeader("Expires", 0); // Causes the proxy cache to see the page as "stale"
        response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility

        setReturnPage(returnPage);
        request.setAttribute("pageTitle", pageTitle);
    }
    
    private StudentBean editStudent(StudentBean user) {
        Map<String, String[]> userFormValues = request.getParameterMap();
        BeanUtilsBean beanManager = BeanUtilsBean.getInstance();
        try {
            beanManager.populate(user, userFormValues);
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.out.println("Error editing Student");
            e.printStackTrace();
        }
        return user;
    }
}
