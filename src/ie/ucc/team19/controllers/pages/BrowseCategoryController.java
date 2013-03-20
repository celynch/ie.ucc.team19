package ie.ucc.team19.controllers.pages;

import ie.ucc.team19.controllers.AbstractController;
import ie.ucc.team19.dao.CourseBean;
import ie.ucc.team19.dao.DBConnectionManager;
import ie.ucc.team19.service.FetchBeanUtils;

public class BrowseCategoryController extends AbstractController{
    public void execute() {
        DBConnectionManager connector = new DBConnectionManager();
        String category = this.getRequest().getParameter("category");
        
        setReturnPage("/browseCategory.jsp");
        getRequest().setAttribute("pageTitle", "Courses in " + category);
        CourseBean[] categoryCourses = new FetchBeanUtils(connector).getCoursesByCourseCategory(category);
        getRequest().setAttribute("categoryCourses", categoryCourses);
    }
}
