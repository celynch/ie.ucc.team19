package ie.ucc.team19.controllers.pages;

import ie.ucc.team19.controllers.AbstractController;
import ie.ucc.team19.dao.CourseBean;
import ie.ucc.team19.dao.DBConnectionManager;
import ie.ucc.team19.service.FetchBeanUtils;

/**
 * Controller class to handle request for the admin dashboard.
 * @author Cormac
 */
public class AdminController extends AbstractController{

    /**
     * Fetches beans from model for display in dashboard view.
     */
    public void execute() {
        DBConnectionManager connector = new DBConnectionManager();
        
        setReturnPage("/admin.jsp");
        String content = request.getParameter("content"); 
        if(content != null) {
            System.out.println(content);
            content = content.replace("'", "''");
            String query = "UPDATE courses SET content = '" + content + "' WHERE courseId = '1'";
            new DBConnectionManager().Insert(query);
        }
        request.setAttribute("pageTitle", "TinyMCE TEST");
        request.setAttribute("includeEditor", true);
        CourseBean[] course = new FetchBeanUtils(connector).getCourseByCourseId("1");
        content = course[0].getContent();
        request.setAttribute("content", content);
    }
}
