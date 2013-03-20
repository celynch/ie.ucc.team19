package ie.ucc.team19.controllers.pages;

import ie.ucc.team19.controllers.AbstractController;
import ie.ucc.team19.dao.CourseBean;
import ie.ucc.team19.dao.DBConnectionManager;
import ie.ucc.team19.service.FetchBeanUtils;

public class AdminController extends AbstractController{

    /**
     *
     */
    public void execute() {
        DBConnectionManager connector = new DBConnectionManager();
        
        setReturnPage("/admin.jsp");
        String content = getRequest().getParameter("content"); 
        if(content != null) {
            System.out.println(content);
            content = content.replace("'", "''");
            String query = "UPDATE courses SET content = '" + content + "' WHERE courseId = '1'";
            new DBConnectionManager().Insert(query);
        }
        getRequest().setAttribute("pageTitle", "TinyMCE TEST");
        getRequest().setAttribute("includeEditor", true);
        CourseBean[] course = new FetchBeanUtils(connector).getCourseByCourseId("1");
        content = course[0].getContent();
        getRequest().setAttribute("content", content);
    }
}
