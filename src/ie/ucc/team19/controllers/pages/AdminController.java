package ie.ucc.team19.controllers.pages;

import ie.ucc.team19.controllers.AbstractController;
import ie.ucc.team19.dao.CourseBean;
import ie.ucc.team19.dao.DBConnectionManager;
import ie.ucc.team19.service.FetchBean;

public class AdminController extends AbstractController{

    /**
     *
     */
    public void execute() {
        setReturnPage("/admin.jsp");
        String spew = getRequest().getParameter("content"); 
        if(spew != null) {
            System.out.println(spew);
            spew = spew.replace("'", "''");
            String query = "UPDATE courses SET content = '" + spew + "' WHERE courseId = '1'";
            new DBConnectionManager().Insert(query);
        }
        getRequest().setAttribute("pageTitle", "TinyMCE TEST");
        getRequest().setAttribute("includeEditor", true);
        CourseBean[] course = new FetchBean().getCourseByCourseId("1");
        String content = course[0].getContent();
        getRequest().setAttribute("content", content);
    }
}
